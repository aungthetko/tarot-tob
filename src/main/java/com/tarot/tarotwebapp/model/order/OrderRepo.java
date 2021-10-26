package com.tarot.tarotwebapp.model.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {

    Order findOrderById(Long id);

    @Modifying
    @Transactional
    @Query("SELECT o FROM Order o WHERE o.status = 'PENDING'")
    List<Order> findOrderByPendingStatus();

    @Modifying
    @Transactional
    @Query("SELECT o FROM Order o WHERE o.status = 'APPROVED'")
    List<Order> findOrderByApprovedStatus();

}
