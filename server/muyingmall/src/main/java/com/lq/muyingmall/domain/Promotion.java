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
    String title;

    @ElementCollection
    List<Product> product = new ArrayList<>();

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

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
