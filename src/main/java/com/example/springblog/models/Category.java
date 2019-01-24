package com.example.springblog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

  @Id @GeneratedValue
  private long id;

  @Column(nullable = false)
  private String name;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
  private List<Product> products;

  public Category() {
  }

  public Category(String name) {
    this.name = name;
  }

  public Category(String name, List<Product> products) {
    this.name = name;
    this.products = products;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

}
