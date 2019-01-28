package com.example.springblog.controllers;

import com.example.springblog.repo.OrderRepository;
import com.example.springblog.repo.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;


}
