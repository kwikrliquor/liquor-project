package com.example.springblog.controllers;

import com.example.springblog.models.Order;
import com.example.springblog.models.User;
import com.example.springblog.repo.OrderRepository;
import com.example.springblog.repo.OrderStatusRepository;
import com.example.springblog.repo.UserRepository;
import com.example.springblog.services.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DriverController {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private OrderStatusRepository orderStatusRepository;

  @GetMapping("/driver_dashboard")
  public String showDriversPage(Model model){

    model.addAttribute("unassignedOrders", orderRepository.findOrdersStatus1());
    model.addAttribute("assignedOrders", orderRepository.findOrdersStatus2());
    model.addAttribute("outForDelivery", orderRepository.findOrdersStatus3());
    model.addAttribute("delivered", orderRepository.findOrdersStatus4());

    return "drivers";
  }

  @PostMapping("/drivers/assign")
  public String assignOrderToDriver(@RequestParam("orderWithStatus1") Long orderWithStatus1) {

    Order placedOrder = orderRepository.findOne(orderWithStatus1);
    placedOrder.setOrderStatusId(orderStatusRepository.findStatusOrderPrepared());
    orderRepository.save(placedOrder);

    return "redirect:/driver_dashboard";
  }

  @PostMapping("/drivers/prepare")
  public String backToPrepare(@RequestParam("orderWithStatus3") Long orderWithStatus3) {

    Order placedOrder = orderRepository.findOne(orderWithStatus3);
    placedOrder.setOrderStatusId(orderStatusRepository.findStatusOrderBackPrepared());
    orderRepository.save(placedOrder);

    return "redirect:/driver_dashboard";
  }

  @PostMapping("/drivers/unassign")
  public String unAssignOrderFromDriver(@RequestParam("orderWithStatus2") Long orderWithStatus2) {

    Order preparedOrder = orderRepository.findOne(orderWithStatus2);
    preparedOrder.setOrderStatusId(orderStatusRepository.findStatusOrderPlaced());
    orderRepository.save(preparedOrder);

    return "redirect:/driver_dashboard";
  }

  @PostMapping("/drivers/delivery")
  public String orderOutForDelivery(@RequestParam("orderWithStatus3") Long orderWithStatus3) {

    Order preparedOrder = orderRepository.findOne(orderWithStatus3);
    preparedOrder.setOrderStatusId(orderStatusRepository.findStatusOrderDelivery());
    orderRepository.save(preparedOrder);

    SmsSender smsSender = new SmsSender();
    smsSender.sendTextDelivery();

    return "redirect:/driver_dashboard";
  }

  @PostMapping("/drivers/delivered")
  public String delivered(@RequestParam("orderWithStatus4") Long orderWithStatus4) {

    Order preparedOrder = orderRepository.findOne(orderWithStatus4);
    preparedOrder.setOrderStatusId(orderStatusRepository.findStatusOrderCompleted());
    orderRepository.save(preparedOrder);

    SmsSender smsSender = new SmsSender();
    smsSender.sendTextDelivered();

    return "redirect:/driver_dashboard";
  }

  @GetMapping("/order_details/{id}")
  public String showDetails(@PathVariable int id, Model model) {
    model.addAttribute("order", orderRepository.findOrdersById(id));
    model.addAttribute("id", id);
    return "orders/order_show";
  }

  @GetMapping("/my-orders")
  public String myOrders(Model model) {
    User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User userId = userRepository.findOne(sessionUser.getId());
    model.addAttribute("orders", orderRepository.findOrdersByUserId(userId));
    return "orders/user_show";
  }
}
