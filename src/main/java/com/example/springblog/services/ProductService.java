package com.example.springblog.services;

import com.example.springblog.models.Category;
import com.example.springblog.models.Product;
import com.example.springblog.repo.CategoryRepository;
import com.example.springblog.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository postsdao;
    private final CategoryRepository catRepo;

//    private List<Product> products;


    public ProductService(ProductRepository postsdao, CategoryRepository catRepo) {
        this.postsdao = postsdao;
        this.catRepo = catRepo;
    }

    public List<Product> getAll() {
        return (List<Product>) postsdao.findAll();
    }

    public List<Category> catGetAll() {
        return (List<Category>) catRepo.findAll();
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

    public Category catCreate(Category category) {
        catRepo.save(category);
        return category;
    }

    public Category catDelete(Category category) {
        catRepo.delete(category);
        return category;
    }

    public Category catEdit(Category category) {
        catRepo.save(category);
        return category;
    }



    public List<Product> beer() {
        return (List<Product>) postsdao.findBeer();
    }

//    public Product wine(Product product) {
//        postsdao.findWine(product.getCategory().getId());
//        return product;
//    }
//
//    public Product liquor(Product product) {
//        postsdao.findLiquor(product.getCategory().getId());
//        return product;
//    }
}
