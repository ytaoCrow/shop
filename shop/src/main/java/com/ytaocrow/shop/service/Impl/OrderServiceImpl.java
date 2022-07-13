package com.ytaocrow.shop.service.Impl;

import com.ytaocrow.shop.dao.OrderDao;
import com.ytaocrow.shop.dao.ProductDao;
import com.ytaocrow.shop.dto.Buyitem;
import com.ytaocrow.shop.dto.CreateOrderRequest;
import com.ytaocrow.shop.model.Order;
import com.ytaocrow.shop.model.OrderItem;
import com.ytaocrow.shop.model.Product;
import com.ytaocrow.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (Buyitem buyitem : createOrderRequest.getBuyItemList()){

            Product product = productDao.getProductById(buyitem.getProductId());

            //計算總額
            int amount = buyitem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            //轉換 BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();

            orderItem.setProductId(buyitem.getProductId());
            orderItem.setQuantity(buyitem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }


        //創建訂單
        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }
}
