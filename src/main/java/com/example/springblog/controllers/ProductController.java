package com.example.springblog.controllers;

import com.example.springblog.models.Category;
import com.example.springblog.models.Product;
import com.example.springblog.models.User;
import com.example.springblog.repo.CategoryRepository;
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

    @GetMapping("/products")
    public String show(Model model) {

        model.addAttribute("products", productService.getAll());
        return "products/index";
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
        return "redirect:/products";
        // save the ad...
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
        return "redirect:/products";
    }

    @PostMapping("/products/{id}/edit")
    public String edit(@ModelAttribute Product product) {
        productService.edit(product);
        return "redirect:/products";
        // save the ad...
    }
}