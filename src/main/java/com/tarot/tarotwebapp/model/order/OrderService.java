package com.tarot.tarotwebapp.model.order;

import java.util.List;

public interface OrderService {

    Order placeOrder(Order order);

    List<Order> findAllOrder();

    String confirmOrder(Long id);

    List<Order> findOrderByPendingStatus();

    List<Order> findOrderByApprovedStatus();

    Order findOrderById(Long id);

    Order saveOrder(Order order);

    int getCountByPendingStatus();
}
