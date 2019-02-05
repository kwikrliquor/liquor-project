package com.example.springblog.services;

import com.example.springblog.exception.NotEnoughProductsInStockException;
import com.example.springblog.models.Order;
import com.example.springblog.models.Product;
import com.example.springblog.models.User;
import com.example.springblog.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartService {
  private final ProductOrderRepository productOrderRepository;
  private final OrderRepository orderRepo;
  private final OrderStatusRepository orderStatusRepo;
  private final UserRepository userRepo;
  private final ProductRepository productRepository;

  private Map<Product, Integer> products = new HashMap<>();

  @Autowired
  public ShoppingCartService(ProductOrderRepository productOrderRepository, OrderRepository orderRepo, OrderStatusRepository orderStatusRepo, UserRepository userRepo, ProductRepository productRepository) {
    this.productOrderRepository = productOrderRepository;
    this.orderRepo = orderRepo;
    this.orderStatusRepo = orderStatusRepo;
    this.userRepo = userRepo;
    this.productRepository = productRepository;
  }

  /**
   * If product is in the map just increment quantity by 1.
   * If product is not in the map with, add it with quantity 1
   *
   * @param product
   */
  public void addProduct(Product product) {
    if (products.containsKey(product)) {
      products.replace(product, products.get(product) + 1);
    } else {
      products.put(product, 1);
    }
//        //REALLY GOOD LOOP FOR HASHMAPS, MUST USE
//        for(Map.Entry<Product, Integer> entry: products.entrySet()) {
//            System.out.println(entry.getKey().getId() + " : " + entry.getValue());
//        }
  }

  /**
   * If product is in the map with quantity > 1, just decrement quantity by 1.
   * If product is in the map with quantity 1, remove it from map
   *
   * @param product
   */
  public void removeProduct(Product product) {
    if (products.containsKey(product)) {
      if (products.get(product) > 1)
        products.replace(product, products.get(product) - 1);
      if (products.get(product) == 1) {
        products.remove(product);
      }
    }
  }

  /**
   * @return unmodifiable copy of the map
   */
  public Map<Product, Integer> getProductsInCart() {
    return Collections.unmodifiableMap(products);
  }

  /**
   * Checkout will rollback if there is not enough of some product in stock
   *
   * @throws NotEnoughProductsInStockException
   */
  public void checkout(String address) throws NotEnoughProductsInStockException {
    Product product;
    for (Map.Entry<Product, Integer> entry : products.entrySet()) {
      // Refresh quantity for every product before checking
      product = productRepository.findOne(entry.getKey().getId());
      if (product.getQuantity() < entry.getValue())
        throw new NotEnoughProductsInStockException(product);
      entry.getKey().setQuantity(product.getQuantity() - entry.getValue());
    }
//        if (userRepo.verificationTrue(true)) {
    newOrder(address);
//        System.out.println(products.get(products.keySet()));
//        System.out.println(products.get(products.entrySet()));
//        }
    productRepository.save(products.keySet());
    productRepository.flush();
    products.clear();
  }

  public void newOrder(String address) {
    User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String timeStamp = new SimpleDateFormat("MM/dd/yyyy" + "\n" + "HH:mm:ss").format(new Date());
    Order order[] = {
        //NEED TO GRAB THE temp_address from the shoppingCart.html input field, google autofill works
        new Order(timeStamp, address, orderStatusRepo.findStatusOrderPlaced(), userRepo.findById(sessionUser.getId()))
    };
    orderRepo.save(Arrays.asList(order));
  }

  public BigDecimal getTotal() {
    return products.entrySet().stream()
        .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
        .reduce(BigDecimal::add)
        .orElse(BigDecimal.ZERO);
  }

}
