package com.example.springblog.services;

import com.example.springblog.models.Product;
import com.example.springblog.models.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository postsdao;

//    private List<Product> posts;

    public ProductService(ProductRepository postsdao) {
        this.postsdao = postsdao;
    }

    public List<Product> getAll() {
        return (List<Product>) postsdao.findAll();
    }

    public Product findOne(int id) {
        return postsdao.findOne(id);
    }

    public Product create(Product product) {
        postsdao.save(product);
        return product;
    }

    public Product delete(Product product) {
        postsdao.delete(product);
        return product;
    }

    public Product edit(Product product) {
        postsdao.save(product);
        return product;
    }
}
