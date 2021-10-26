package com.tarot.tarotwebapp.model.order;

import com.tarot.tarotwebapp.enumeration.Status;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.tarot.tarotwebapp.enumeration.Status.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepo orderRepo;

    @Override
    public List<Order> findAllOrder() {
        return orderRepo.findAll();
    }

    @Override
    public Order findOrderById(Long id) {
        return orderRepo.findById(id).get();
    }

    @Override
    public Order saveOrder(Order order) {
        if(order.getStatus() == APPROVED){
            order.setIsConfirmed(Boolean.TRUE);
        }
        return orderRepo.save(order);
    }

    @Override
    public Order placeOrder(Order order) {
        Long orderCode = Long.parseLong(generateId());
        order.setOrderCode(orderCode);
        order.setOrderAt(LocalDateTime.now());
        order.setIsConfirmed(Boolean.FALSE);
        if (order.getIsConfirmed() == Boolean.FALSE){
            order.setStatus(PENDING);
        }else{
            order.setStatus(APPROVED);
        }
        return orderRepo.save(order);
    }

    @Override
    public String confirmOrder(Long id) {
        Order order = orderRepo.findOrderById(id);
        if (order == null){
            throw new IllegalStateException( id + " : order not found");
        }
        order.setIsConfirmed(Boolean.TRUE);
        order.setStatus(APPROVED);
        orderRepo.save(order);
        return "confirmed";
    }

    @Override
    public int getCountByPendingStatus() {
        return orderRepo.getCountByPendingStatus();
    }

    @Override
    public List<Order> findOrderByPendingStatus() {
        return orderRepo.findOrderByPendingStatus();
    }

    @Override
    public List<Order> findOrderByApprovedStatus() {
        return orderRepo.findOrderByApprovedStatus();
    }

    private String generateId(){
        return RandomStringUtils.randomNumeric(6);
    }
}
