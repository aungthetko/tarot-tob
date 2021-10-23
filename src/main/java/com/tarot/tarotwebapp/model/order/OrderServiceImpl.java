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
    public Order placeOrder(Order order) {
        Long id = Long.parseLong(generateId());
        order.setId(id);
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

    private String generateId(){
        return RandomStringUtils.randomNumeric(6);
    }
}
