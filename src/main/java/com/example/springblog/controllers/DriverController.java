package com.example.springblog.controllers;

import com.example.springblog.models.Order;
import com.example.springblog.models.OrderStatus;
import com.example.springblog.models.ProductOrder;
import com.example.springblog.models.User;
import com.example.springblog.repo.*;
import com.example.springblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;

@Controller
public class DriverController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @GetMapping("/drivers")
    public String showDriversPage(Model model){

        model.addAttribute("unassignedOrders", orderRepository.findOrdersStatus1());
        model.addAttribute("assignedOrders", orderRepository.findOrdersStatus2());
        return "drivers";
    }

    @PostMapping("/drivers/assign")
    public String assignOrderToDriver(@RequestParam("orderWithStatus1") Long orderWithStatus1) {


        Order placedOrder = orderRepository.findOne(orderWithStatus1);

//        placedOrder.setOrderStatusId(orderStatusRepository.findById(2));
        placedOrder.setOrderStatusId(orderStatusRepository.findStatusOrderPrepared());

        orderRepository.save(placedOrder);


        return "redirect:/drivers";
    }

    @PostMapping("/drivers/unassign")
    public String unAssignOrderFromDriver(@RequestParam("orderWithStatus2") Long orderWithStatus2) {


        Order preparedOrder = orderRepository.findOne(orderWithStatus2);

//        preparedOrder.setOrderStatusId(orderStatusRepository.findById(2));
        preparedOrder.setOrderStatusId(orderStatusRepository.findStatusOrderPlaced());

        orderRepository.save(preparedOrder);

        return "redirect:/drivers";
    }

    @GetMapping("/order_details/{id}")
    public String showDetails(@PathVariable int id, Model model) {
//        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("user", sessionUser);
        model.addAttribute("order", orderRepository.findOrdersById(id));
        model.addAttribute("id", id);
        return "orders/order_show";
    }

}
