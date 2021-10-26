package com.tarot.tarotwebapp.resource;

import com.tarot.tarotwebapp.model.order.Order;
import com.tarot.tarotwebapp.model.order.OrderService;
import com.tarot.tarotwebapp.model.user.User;
import com.tarot.tarotwebapp.model.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class TarotWebResource {

    private final OrderService orderService;
    private final UserService userService;

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
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("listOrder", orderService.findAllOrder());
        model.addAttribute("pendingNumber", orderService.getCountByPendingStatus());
        return "admin-home";
    }

    @RequestMapping("/admin/save/user")
    public String saveLoginUser(@ModelAttribute("user") User user){
        userService.addNewUser(user);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/pending/all")
    public String viewPendingOrderPage(Model model){
        model.addAttribute("pendingOrder", orderService.findOrderByPendingStatus());
        return "pending-order";
    }

    @RequestMapping("/admin/approved/all")
    public String viewApprovedOrderPage(Model model){
        model.addAttribute("approvedOrder", orderService.findOrderByApprovedStatus());
        return "approved-order";
    }

    @PostMapping(value = "/saveorder")
    public String saveOrder(@ModelAttribute("order") Order order){
        orderService.placeOrder(order);
        return "order-done";
    }

    @GetMapping(value = "/employee/confirm/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        Order order = orderService.findOrderById(id);
        model.addAttribute("order", order);
        return "confirm-order";
    }

    @PostMapping(value = "/employee/update/{id}")
    public String updateOrder(@PathVariable("id") Long id, Order order, BindingResult result, Model model){
        if (result.hasErrors()){
            order.setId(id);
            return "confirm-order";
        }
        orderService.saveOrder(order);
        model.addAttribute("orders", orderService.findAllOrder());
        return "redirect:/employee";
    }

}
