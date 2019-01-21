package com.lq.muyingmall.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    private String categoryName;

    @Column(nullable = false, unique = true)
    private int categoryId;

    public Category() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
