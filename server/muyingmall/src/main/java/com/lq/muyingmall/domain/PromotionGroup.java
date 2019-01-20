package com.lq.muyingmall.domain;


import java.util.List;

public class PromotionGroup {
    private List<Promotion> promotionList;

    private List<News> newsList;

    public PromotionGroup() {
    }

    public List<Promotion> getPromotionList() {
        return promotionList;
    }

    public void setPromotionList(List<Promotion> promotionList) {
        this.promotionList = promotionList;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}
