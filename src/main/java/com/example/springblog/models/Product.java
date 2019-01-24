package com.example.springblog.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

  @Id @GeneratedValue
  private long id;

  @Column(nullable = true)
  private Integer quantity;

  @Column(nullable = true)
  private double price;

  @Column(name = "img_url", nullable = true)
  private String imgUrl;

  @ManyToOne
  @JoinColumn(name = "cat_id")
  private Category category; // Not sure if this is correct

  @Column(nullable = false, length = 1000)
  private String name;

  @Column(nullable = false, length = 10000)
  private String type;

  @Column(nullable = false, length = 10000)
  private String description;

  @ManyToOne
  @JoinColumn (name = "user_id")
  private User user;

  public Product() {
  }

  public Product(String name, String type, String description, User user) {
    this.name = name;
    this.type = type;
    this.description = description;
    this.user = user;
  }

  public Product(String name, String type, String description, Category category) {
    this.name = name;
    this.type = type;
    this.description = description;
    this.category = category;
  }

  public Product(String name, String type, String description, Category category, User user,
      double price) {
    this.name = name;
    this.type = type;
    this.description = description;
    this.category = category;
    this.user = user;
    this.price = price;
  }

  public Product(Integer quantity, double price, String imgUrl, Category category, String name,
      String type, String description, User user) {
    this.quantity = quantity;
    this.price = price;
    this.imgUrl = imgUrl;
    this.category = category;
    this.name = name;
    this.type = type;
    this.description = description;
    this.user = user;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
