package com.lq.muyingmall.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> queryAllByPromotionId(int promotionId);

    List<Product> queryAllByCategoryId(int categoryId);

    Product queryById(long id);


}