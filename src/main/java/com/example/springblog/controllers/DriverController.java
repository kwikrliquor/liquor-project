package com.example.springblog.controllers;

import com.example.springblog.models.User;
import com.example.springblog.repo.UserRepository;
import com.example.springblog.repo.UserRoleRepository;
import com.example.springblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DriverController {

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleRepository userRoles;

    @Autowired
    private UserService usersService;

    @GetMapping("/drivers")
    public String showDriversPage(Model model){
        model.addAttribute("user", new User());
        return "drivers";
    }


}
