package com.example.springblog.controllers;

import com.example.springblog.models.Product;
import com.example.springblog.models.User;
import com.example.springblog.services.ProductService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService ps) {
        this.productService = ps;
    }

    @GetMapping("/posts")
    public String show(Model model) {
        model.addAttribute("posts", productService.getAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postid(@PathVariable int id, Model model) {
        model.addAttribute("post", productService.findOne(id));
        model.addAttribute("id", id);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Product());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String create(@ModelAttribute Product product) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        product.setUser(user);
        productService.create(product);
        return "redirect:/posts";
        // save the ad...
    }

    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("post", productService.findOne(id));
        return "posts/edit";
    }

    @GetMapping("/posts/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("post", productService.findOne(id));
        productService.delete(productService.findOne(id));
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/edit")
    public String edit(@ModelAttribute Product product) {
        productService.edit(product);
        return "redirect:/posts";
        // save the ad...
    }
}