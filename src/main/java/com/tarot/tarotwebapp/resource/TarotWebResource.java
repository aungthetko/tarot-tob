package com.tarot.tarotwebapp.resource;

import com.tarot.tarotwebapp.model.order.Order;
import com.tarot.tarotwebapp.model.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TarotWebResource {

    private final OrderService orderService;

    @RequestMapping("/")
    public String viewIndexPage(Model model){
        Order order = new Order();
        model.addAttribute("order", order);
        return "index";
    }

    @RequestMapping("/employee")
    public String viewEmployeeHomePage(Model model){
        model.addAttribute("listOrder", orderService.findAllOrder());
        return "employee-home";
    }


    @RequestMapping("/admin")
    public String viewAdminHomePage(Model model){
        model.addAttribute("listOrder", orderService.findAllOrder());
        return "admin-home";
    }
    @PostMapping(value = "/saveorder")
    public String saveOrder(@ModelAttribute("order") Order order){
        orderService.placeOrder(order);
        return "order-done";
    }

    @GetMapping("/employee/confirm/{id}")
    public String confirmOrder(@PathVariable("id") Long id){
        return orderService.confirmOrder(id);
    }

}
