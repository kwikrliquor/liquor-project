package com.example.springblog.controllers;

import com.example.springblog.exception.NotEnoughProductsInStockException;
import com.example.springblog.repo.OrderRepository;
import com.example.springblog.services.ProductService;
import com.example.springblog.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService, OrderRepository orderRepository) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("shoppingCart");
        modelAndView.addObject("products", shoppingCartService.getProductsInCart());
        modelAndView.addObject("total", shoppingCartService.getTotal().toString());
        modelAndView.addObject("total_stripe", shoppingCartService.getTotal().toString().replace(".", ""));
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

    @PostMapping("/shoppingCart/removeProduct/{productId}")
    public ModelAndView removeProductFromCart(@PathVariable("productId") Long productId) {
        productService.findById(productId).ifPresent(shoppingCartService::removeProduct);
        return shoppingCart();
    }

    @GetMapping("/shoppingCart/checkout")
    public ModelAndView order(@RequestParam("checkout_address") String checkout_address) {
        ModelAndView modelAndView = new ModelAndView("checkoutSuccess");
        try {
            shoppingCartService.checkout(checkout_address);
//            shoppingCartService.newOrder(checkout_address);
        } catch (NotEnoughProductsInStockException e) {
            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
        }
        modelAndView.addObject("order_num", orderRepository.findAll().size());
//        modelAndView.addObject("order_address", orderRepository.findAll().size());

        return modelAndView;
    }

//    @PostMapping("/shoppingCart/checkout")
//    public ModelAndView orderPost() {
//        try {
//            shoppingCartService.checkout();
//            shoppingCartService.newOrder();
//        } catch (NotEnoughProductsInStockException e) {
//            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
//        }
//        return shoppingCart();
//    }

//    @PostMapping("/drivers/delivered")
//    public String delivered(@RequestParam("checkout_address") Long checkout_address, Model model) {
//        model.addAttribute("unassignedOrders", orderRepository.findOrdersStatus1());
//
//        Order tempAddress = orderRepository.findOne(checkout_address);
//        tempAddress.setTempAddress(orderRepository.);
//        orderRepository.save(preparedOrder);
//
//        return "redirect:/driver_dashboard";
//    }
}
