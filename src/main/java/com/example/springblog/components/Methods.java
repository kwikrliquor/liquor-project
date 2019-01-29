package com.example.springblog.components;

import com.example.springblog.models.Order;
import com.example.springblog.models.User;
import com.example.springblog.repo.CategoryRepository;
import com.example.springblog.repo.OrderRepository;
import com.example.springblog.repo.OrderStatusRepository;
import com.example.springblog.repo.ProductRepository;
import com.example.springblog.repo.UserRepository;
import com.example.springblog.repo.UserRoleRepository;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
        //NEED TO GRAB THE temp_address from the shoppingCart.html input field, google autofill works
        new Order(timeStamp, orderStatusRepo.findStatusOrderPlaced(), userRepo.findById(sessionUser.getId()))
    };
    orderRepo.save(Arrays.asList(order));
  }


}
