package com.lq.muyingmall.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * 活动
 */
@Entity
public class Promotion {
    @Column(nullable = false)
    private String title;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pomotionId;
    @Transient
    private List<Product> productsList;

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPomotionId() {
        return pomotionId;
    }

    public void setPomotionId(int pomotionId) {
        this.pomotionId = pomotionId;
    }
}
