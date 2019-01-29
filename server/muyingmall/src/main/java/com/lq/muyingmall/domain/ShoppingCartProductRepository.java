package com.lq.muyingmall.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartProductRepository extends JpaRepository<ShoppingCartProduct, Long> {

    void deleteByUserNameAndProductId(String userName, String productId);

    List<ShoppingCartProduct> queryAllByUserName(String userName);
}