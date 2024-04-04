package com.redhat.coolstore.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

public class OrderItem extends PanacheEntity {
    private static final long serialVersionUID = 64565445665456666L;

    public int quantity;

    public String productId;

    public OrderItem() {}

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
