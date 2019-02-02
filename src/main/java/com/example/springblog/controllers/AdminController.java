package com.example.springblog.controllers;

import com.example.springblog.models.User;
import com.example.springblog.repo.OrderRepository;
import com.example.springblog.repo.OrderStatusRepository;
import com.example.springblog.repo.UserRepository;
import com.example.springblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderStatusRepository orderStatusRepository;

  @GetMapping("/admin-dashboard")
  public String showAdminPage(Model model){

    model.addAttribute("existingUsers", userService.getAll());
    model.addAttribute("unverifiedUsers", userRepository.findUnverifiedUsers());

    return "admin";
  }

  @GetMapping("/user-details/{id}")
  public String showUserDetails(@PathVariable long id, Model model){

    model.addAttribute("user", userRepository.findById(id));
    model.addAttribute("id", id);
    return "users/profile-edit";

  }

  @PostMapping("admin-dashboard/verify")
  public String setToPrepare(@RequestParam("unverifiedUser") Long unverifiedUser) {

    User verifiedUser = userRepository.findOne(unverifiedUser);
    verifiedUser.setAgeVerified(true);
    userRepository.save(verifiedUser);

    return "redirect:/admin-dashboard";
  }

}
