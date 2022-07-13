package com.ytaocrow.shop.service;

import com.ytaocrow.shop.dto.CreateOrderRequest;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

}
