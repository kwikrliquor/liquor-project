package com.example.springblog.controllers;

import com.example.springblog.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

  private ProductService productService;

  public HomeController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("products", productService.getAll());
    return "home";
  }

  @GetMapping("/error")
  @ResponseBody
  public String not() {
    return "Page not Found!";
  }

}