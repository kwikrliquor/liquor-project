package com.example.springblog.controllers;

import com.example.springblog.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

  private ProductService productService;

  public HomeController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/")
  public ModelAndView home() {
    ModelAndView modelAndView = new ModelAndView("home");
    modelAndView.addObject("products", productService.getAll());
    return modelAndView;
  }

  @GetMapping("/error")
  @ResponseBody
  public String not() {
    return "Page not Found!";
  }

  @GetMapping("/about")
  public String check() {
    return "about";
  }

}