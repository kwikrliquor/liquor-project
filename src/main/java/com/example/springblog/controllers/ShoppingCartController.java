package com.example.springblog.controllers;

import com.example.springblog.components.Methods;
import com.example.springblog.exception.NotEnoughProductsInStockException;
import com.example.springblog.repo.OrderRepository;
import com.example.springblog.services.ProductService;
import com.example.springblog.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final Methods methods;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService, OrderRepository orderRepository, Methods methods) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.methods = methods;
    }

    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("shoppingCart");
        modelAndView.addObject("products", shoppingCartService.getProductsInCart());
        modelAndView.addObject("total", shoppingCartService.getTotal().toString());
        return modelAndView;
    }

    @GetMapping("/shoppingCart/addProduct/{productId}")
    public ModelAndView addProduct(@PathVariable("productId") Long productId) {
        ModelAndView modelAndView = new ModelAndView("shoppingCart");
        return modelAndView;
    }

    @PostMapping("/shoppingCart/addProduct/{productId}")
    public ModelAndView addProductToCart(@PathVariable("productId") Long productId) {
        productService.findById(productId).ifPresent(shoppingCartService::addProduct);
        return shoppingCart();
    }

    @GetMapping("/shoppingCart/removeProduct/{productId}")
    public ModelAndView removeProductFromCart(@PathVariable("productId") Long productId) {
        productService.findById(productId).ifPresent(shoppingCartService::removeProduct);
        return shoppingCart();
    }

    @GetMapping("/shoppingCart/checkout")
    public ModelAndView order() {
        ModelAndView modelAndView = new ModelAndView("checkoutSuccess");
        try {
            shoppingCartService.checkout();
        } catch (NotEnoughProductsInStockException e) {
            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
        }
        modelAndView.addObject("order_num", orderRepository.findAll().size());
        return modelAndView;
    }
}
