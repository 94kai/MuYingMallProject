package com.lq.muyingmall.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author xuekai1
 * @date 2019/1/29
 */
@Entity
public class ShoppingCartProduct implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column()
    private String userName;
    @Column()
    private String productId;

    public ShoppingCartProduct() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
