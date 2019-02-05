package com.example.springblog.controllers;

import com.example.springblog.models.Product;
import com.example.springblog.models.User;
import com.example.springblog.services.ProductService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

  private ProductService productService;

  public ProductController(ProductService ps) {
    this.productService = ps;
  }

//  @GetMapping("/cart")
//  public String cart(Model model) {
//    model.addAttribute()
//  }

  @GetMapping("/products/beer")
  public String beer(Model model) {
    model.addAttribute("products", productService.beer());
    return "home";
  }

  @GetMapping("/products/wine")
  public String wine(Model model) {
    model.addAttribute("products", productService.wine());
    return "home";
  }

  @GetMapping("/products/liquor")
  public String liquor(Model model) {
    model.addAttribute("products", productService.liquor());
    return "home";
  }

  @GetMapping("/products/{id}")
  public String postid(@PathVariable int id, Model model) {
    model.addAttribute("product", productService.findOne(id));
    model.addAttribute("id", id);
    return "products/show";
  }

  @GetMapping("/products/create")
  public String showCreateForm(Model model) {
    model.addAttribute("product", new Product());
    return "products/create";
  }

  @PostMapping("/products/create")
  public String create(@ModelAttribute Product product) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    product.setUser(user);
    productService.create(product);
    return "redirect:/home";
  }

  @GetMapping("/products/{id}/edit")
  public String edit(@PathVariable int id, Model model) {
    model.addAttribute("product", productService.findOne(id));
    return "products/edit";
  }

  @GetMapping("/products/{id}/delete")
  public String delete(@PathVariable int id, Model model) {
    model.addAttribute("product", productService.findOne(id));
    productService.delete(productService.findOne(id));
    return "redirect:/home";
  }

  @PostMapping("/products/{id}/edit")
  public String edit(@ModelAttribute Product product) {
    productService.edit(product);
    return "redirect:/home";
  }

  @GetMapping("/google")
    public String check() {
    return "google";
  }

}