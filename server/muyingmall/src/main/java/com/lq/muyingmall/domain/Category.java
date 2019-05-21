package com.lq.muyingmall.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category implements Serializable {
    @Column(nullable = false, unique = true)
    private String categoryName;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int categoryId;

    public Category() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
