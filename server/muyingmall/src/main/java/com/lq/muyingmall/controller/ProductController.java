package com.lq.muyingmall.controller;

import com.lq.muyingmall.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api")
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/appProducts", method = {RequestMethod.POST})
    public BaseResponse addProducts(@RequestBody List<Product> productList) {
        for (Product product : productList) {
            try {
                productRepository.deleteById(product.getId());
            } catch (EmptyResultDataAccessException e) {
                logger.error("删除失败，并无大碍");
            }
            productRepository.save(product);
        }
        return new BaseResponse(0, "添加成功");
    }

    /**
     * 查询商品列表，通过分类id，如果传入-1，返回所有列表
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "/queryProductsByCategory", method = {RequestMethod.GET})
    public BaseResponse<List<Product>> queryProductsByCategory(int categoryId) {
        List<Product> products;

        if (categoryId == -1) {
            products = productRepository.findAll();
        } else {
            products = productRepository.queryAllByCategoryId(categoryId);
        }
        BaseResponse<List<Product>> response = new BaseResponse<>();
        response.setData(products);
        return response;
    }

}
