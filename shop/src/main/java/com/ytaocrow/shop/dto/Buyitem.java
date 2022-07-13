package com.ytaocrow.shop.dto;

import javax.validation.constraints.NotNull;

public class Buyitem {

    @NotNull
    private Integer productId;
   @NotNull
    private Integer quantity;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
