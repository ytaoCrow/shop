package com.ytaocrow.shop.service;

import com.ytaocrow.shop.dto.CreateOrderRequest;
import com.ytaocrow.shop.dto.OrderQueryParams;
import com.ytaocrow.shop.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);
    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

}
