package com.example.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Welcome this is the landing page!";
    }
    @GetMapping("/error")
    @ResponseBody
    public String not() {
        return "Page not Found!";
    }
}