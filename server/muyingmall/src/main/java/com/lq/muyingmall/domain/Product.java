package com.lq.muyingmall.domain;

import javax.persistence.*;

@Embeddable
public class Product {
//    @Id
//    @GeneratedValue
//    private long id;

    @Column(nullable = false)
    private String productImg;


    @Column(nullable = false)
    private String title;

    public Product() {
    }

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
