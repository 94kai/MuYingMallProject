package com.lq.muyingmall.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Banner {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    String bannerImg;

    public Banner() {
    }


    public Banner(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }
}
