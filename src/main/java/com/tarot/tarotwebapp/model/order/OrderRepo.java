package com.tarot.tarotwebapp.model.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {

    Order findOrderById(Long id);
}
