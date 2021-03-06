package com.ytaocrow.shop.service.Impl;

import com.ytaocrow.shop.dao.OrderDao;
import com.ytaocrow.shop.dao.ProductDao;
import com.ytaocrow.shop.dao.UserDao;
import com.ytaocrow.shop.dto.Buyitem;
import com.ytaocrow.shop.dto.CreateOrderRequest;
import com.ytaocrow.shop.dto.OrderQueryParams;
import com.ytaocrow.shop.model.Order;
import com.ytaocrow.shop.model.OrderItem;
import com.ytaocrow.shop.model.Product;
import com.ytaocrow.shop.model.User;
import com.ytaocrow.shop.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(orderId);

        order.setOrderItemList(orderItemList);
        return order;
    }

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        return orderDao.countOrder(orderQueryParams);
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        List<Order> orderList = orderDao.getOrders(orderQueryParams);

        for (Order order  : orderList){
            List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(order.getOrderId());

            order.setOrderItemList(orderItemList);
        }
        return orderList;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        User user = userDao.getUserById(userId);

        if(user == null){
            log.warn("??? userId {} ?????????", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (Buyitem buyitem : createOrderRequest.getBuyItemList()){

            Product product = productDao.getProductById(buyitem.getProductId());

            if(product == null){
                log.warn("?????? {} ?????????", buyitem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }else if (product.getStock() < buyitem.getQuantity()){
                log.warn("?????? {} ????????????????????????????????? {} ?????????????????? {}",
                        buyitem.getProductId(), product.getStock(), buyitem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            //??????????????????
            productDao.updateStock(product.getProductId(), product.getStock() - buyitem.getQuantity());

            //????????????
            int amount = buyitem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            //?????? BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();

            orderItem.setProductId(buyitem.getProductId());
            orderItem.setQuantity(buyitem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }


        //????????????
        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }
}
