package com.ytaocrow.shop.dto;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class CreateOrderRequest {

    @NotEmpty
    private List<Buyitem> buyItemList;

    public List<Buyitem> getBuyItemList() {
        return buyItemList;
    }

    public void setBuyItemList(List<Buyitem> buyItemList) {
        this.buyItemList = buyItemList;
    }
}
