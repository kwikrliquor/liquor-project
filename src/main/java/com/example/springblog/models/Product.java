package com.example.springblog.models;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;

@Entity
@Table(name = "products")
public class Product {

  @Id @GeneratedValue
  private long id;

  @Column(nullable = true)
  private int stock;

  @Column(nullable = true)
  private int quantity;

  @Column(nullable = true)
  @DecimalMin(value = "0.00", message = "*Price has to be non negative number")
  private BigDecimal price;

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

  public Product(String name, String type, String description, BigDecimal price, Category category) {
    this.name = name;
    this.type = type;
    this.description = description;
    this.price = price;
    this.category = category;
  }

  public Product(String name, String type, String description, BigDecimal price, int quantity, Category category, User user) {
    this.name = name;
    this.type = type;
    this.description = description;
    this.price = price;
    this.quantity = quantity;
    this.category = category;
    this.user = user;
  }

  public Product(String name, String type, String description, BigDecimal price, int quantity, String imgUrl,Category category, User user) {
    this.name = name;
    this.type = type;
    this.description = description;
    this.price = price;
    this.quantity = quantity;
    this.imgUrl = imgUrl;
    this.category = category;
    this.user = user;
  }

  public Product(int stock, int quantity, BigDecimal price, String imgUrl, Category category, String name, String type, String description, User user) {
    this.stock = stock;
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

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Product product = (Product) o;
//
//        return id.equals(product.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return id.hashCode();
//    }

}
