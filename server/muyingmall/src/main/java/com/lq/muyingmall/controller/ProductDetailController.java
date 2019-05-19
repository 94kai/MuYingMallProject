package com.lq.muyingmall.controller;

import com.lq.muyingmall.domain.BaseResponse;
import com.lq.muyingmall.domain.CategoryRepository;
import com.lq.muyingmall.domain.Product;
import com.lq.muyingmall.domain.ProductDetail;
import com.lq.muyingmall.domain.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@RestController
@RequestMapping(value = "/api")
public class ProductDetailController {

    String[] goodsTag = {
            "包邮",
            "运费险",
    };
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/queryProductDetailById", method = {RequestMethod.GET})
    public BaseResponse<ProductDetail> queryProductDetailById(String productId) {
        Random random = new Random();

        Optional<Product> productOptional = productRepository.findById((long) Integer.parseInt(productId));
        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            ProductDetail productDetail = new ProductDetail();

            productDetail.setTitle(product.getTitle());
            productDetail.setId(product.getId());
            productDetail.setOriginal_price(product.getOriginal_price());
            productDetail.setSell_num(product.getSell_num());
            List<String> picList = new ArrayList<>();
            picList.add(product.getImage());
            // TODO: by xk 2019/1/26 下午6:55 添加多个banner图片
//            picList.add(product.getImage());
            productDetail.setPic_list(picList);
            List<String> goodsTag = new ArrayList<>();
            goodsTag.add("包邮");
            goodsTag.add("运费险");
            productDetail.setGoods_tag(goodsTag);
            ProductDetail.Store store = new ProductDetail.Store();
            store.setDelivery(4.8f);
            store.setDsr(4.8f);
            store.setService(4.9f);
            store.setShop_name("LQ母婴旗舰店");
            store.setShop_logo("http://img2.3png.com/6eb7d08b17b675764e4e60506724ee1f1f89.png");
            productDetail.setStore(store);
            productDetail.setDetails("欢迎光临" + store.getShop_name() + "，该商品为" + productDetail.getTitle());


            List<Product> products = productRepository.queryAllByCategoryId(product.getCategoryId());
            int recommendsCount = 10;
            if (products.size() > 0) {
                if (products.size() < recommendsCount) {
                    productDetail.setRecommend(products);
                } else {
                    List<Product> recommends = new ArrayList<>();
                    //生成recommendsCount个随机数
                    HashSet<Integer> integers = new HashSet<>();
                    while (integers.size() < recommendsCount) {
                        int i = random.nextInt(recommendsCount);
                        integers.add(i);
                    }
                    for (Integer integer : integers) {
                        Product product1 = products.get(integer);
                        recommends.add(product1);
                    }
                    productDetail.setRecommend(recommends);
                }
            }
            return new BaseResponse<>(0, productDetail);
        } else {
            return new BaseResponse<>(-1, "不存在", null);
        }
    }


}
