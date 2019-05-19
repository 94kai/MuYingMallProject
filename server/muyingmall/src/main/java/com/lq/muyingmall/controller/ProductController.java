package com.lq.muyingmall.controller;

import com.lq.muyingmall.domain.BaseResponse;
import com.lq.muyingmall.domain.Category;
import com.lq.muyingmall.domain.CategoryRepository;
import com.lq.muyingmall.domain.Product;
import com.lq.muyingmall.domain.ProductRepository;
import com.lq.muyingmall.domain.Promotion;
import com.lq.muyingmall.domain.PromotionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    PromotionRepository promotionRepository;

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
     *
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
        for (Product product : products) {
            int categoryId1 = product.getCategoryId();
            if (categoryId1 >= 0) {
                Category category = categoryRepository.findCategoryByCategoryId(categoryId1);
                if (category != null) {
                    product.setCategoryName(category.getCategoryName());
                }
            }

            int promotionId = product.getPromotionId();
            if (promotionId > 0) {
                Promotion promotion = promotionRepository.findPromotionByPomotionId(promotionId);
                if (promotion != null) {
                    product.setPromotionName(promotion.getTitle());
                }
            }
        }

        BaseResponse<List<Product>> response = new BaseResponse<>();
        response.setData(products);
        return response;
    }

}
