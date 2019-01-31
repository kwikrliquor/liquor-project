package com.example.springblog.services;

import com.example.springblog.models.Category;
import com.example.springblog.models.Product;
import com.example.springblog.repo.CategoryRepository;
import com.example.springblog.repo.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository catRepo;

  public ProductService(ProductRepository productRepository, CategoryRepository catRepo) {
    this.productRepository = productRepository;
    this.catRepo = catRepo;
  }

  public Page<Product> findAllProductsPageable(Pageable pageable) {
    return productRepository.findAll(pageable);
  }

  public Optional<Product> findById(Long id) {
    return productRepository.findById(id);
  }

  public List<Product> getAll() {
    return (List<Product>) productRepository.findAll();
  }

  public List<Category> catGetAll() {
    return (List<Category>) catRepo.findAll();
  }

  public Product findOne(long id) {
    return productRepository.findOne(id);
  }

  public Product create(Product product) {
    productRepository.save(product);
    return product;
  }

  public Product delete(Product product) {
    productRepository.delete(product);
    return product;
  }

  public Product edit(Product product) {
    productRepository.save(product);
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
    return productRepository.findBeer();
  }

  public List<Product> wine() {
    return productRepository.findWine();
  }

  public List<Product> liquor() {
    return productRepository.findLiquor();
  }

}
