package com.lq.muyingmall.controller;

import com.lq.muyingmall.domain.BaseResponse;
import com.lq.muyingmall.domain.Product;
import com.lq.muyingmall.domain.ProductRepository;
import com.lq.muyingmall.domain.ShoppingCartProduct;
import com.lq.muyingmall.domain.ShoppingCartProductRepository;
import com.lq.muyingmall.utils.TextUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuekai1
 * @date 2019/1/29
 */
@RestController
@RequestMapping(value = "/api")
public class ShoppingCartController {
    @Autowired
    ShoppingCartProductRepository shoppingCartProductRepository;
    @Autowired
    ProductRepository productRepository;


    @RequestMapping(value = "/addProductToCart", method = {RequestMethod.GET})
    public BaseResponse addProductToCart(String userName, String productId, @RequestHeader(value = "token") String token) {
        if (!TextUtils.equals(LoginController.tokenMap.get(userName), token)) {
            return new BaseResponse(-1, "token校验失败");
        }
        ShoppingCartProduct shoppingCartProduct = new ShoppingCartProduct();
        shoppingCartProduct.setProductId(productId);
        shoppingCartProduct.setUserName(userName);
        shoppingCartProductRepository.save(shoppingCartProduct);
        return new BaseResponse(0, "添加成功");
    }

    @RequestMapping(value = "/queryCartByUserName", method = {RequestMethod.GET})
    public BaseResponse<List<Product>> queryCartByUserName(String userName, @RequestHeader(value = "token") String token) {
        if (!TextUtils.equals(LoginController.tokenMap.get(userName), token)) {
            return new BaseResponse<>(-1, "token校验失败");
        }
        try {
            ArrayList<Product> products = new ArrayList<>();
            List<ShoppingCartProduct> shoppingCartProducts = shoppingCartProductRepository.queryAllByUserName(userName);
            for (ShoppingCartProduct shoppingCartProduct : shoppingCartProducts) {
                Product product = productRepository.queryById(Long.parseLong(shoppingCartProduct.getProductId()));
                products.add(product);
            }
            return new BaseResponse<>(0, products);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResponse<>(-1, "查询失败");
    }

    @RequestMapping(value = "/deleteProductFromCart", method = {RequestMethod.GET})
    public BaseResponse deleteProductFromCart(String userName, String productId, @RequestHeader(value = "token") String token) {
        if (!TextUtils.equals(LoginController.tokenMap.get(userName), token)) {
            return new BaseResponse(-1, "token校验失败");
        }
        try {
            shoppingCartProductRepository.deleteByUserNameAndProductId(userName, productId);
            return new BaseResponse<>(0, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResponse<>(-1, "删除失败");
    }
}
