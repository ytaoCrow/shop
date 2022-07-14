package com.ytaocrow.shop.service;

import com.ytaocrow.shop.dto.CreateOrderRequest;
import com.ytaocrow.shop.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

}
