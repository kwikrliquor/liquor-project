package com.example.springblog.components;

import com.example.springblog.models.Order;
import com.example.springblog.models.User;
import com.example.springblog.models.UserRole;
import com.example.springblog.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Component
public class Methods {
    private final UserRoleRepository roleRepo;
    private final CategoryRepository catRepo;
    private final ProductRepository prodRepo;
    private final OrderStatusRepository orderStatusRepo;
    private final UserRepository userRepo;
    private final OrderRepository orderRepo;
    private final PasswordEncoder passwordEncoder;

    public Methods(UserRoleRepository roleRepo, CategoryRepository catRepo, ProductRepository prodRepo, OrderStatusRepository orderStatusRepo, UserRepository userRepo, OrderRepository orderRepo, PasswordEncoder passwordEncoder) {
        this.roleRepo = roleRepo;
        this.catRepo = catRepo;
        this.prodRepo = prodRepo;
        this.orderStatusRepo = orderStatusRepo;
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void newOrder() {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String timeStamp = new SimpleDateFormat("MM/dd/yyyy" + "\n" + "HH:mm:ss").format(new Date());
        Order order[] = {
                new Order(timeStamp, orderStatusRepo.findStatusOrderPlaced(), userRepo.findById(sessionUser.getId()))
        };
        orderRepo.save(Arrays.asList(order));
    }
}
