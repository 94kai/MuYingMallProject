package com.lq.muyingmall.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 活动
 */
@Entity
public class Promotion {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, unique = true)
    private int pomotionId;
    @Transient
    private List<Product> productsList;

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
