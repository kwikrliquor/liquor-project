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
    private int stock;

    @Column(nullable = true)
    private double cost;

    @Column(name = "img_url", nullable = true)
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Category category; // Not sure if this is correct

    @Column(nullable = false, length = 1000)
    private String name;

    @Column(nullable = false, length = 10000)
    private String details;

    @Column(nullable = false, length = 10000)
    private String description;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    public Product() {
    }

    public Product(String name, String details, String description, User user) {
        this.name = name;
        this.details = details;
        this.description = description;
        this.user = user;
    }

    public Product(String name, String details, String description, Category category) {
        this.name = name;
        this.details = details;
        this.description = description;
        this.category = category;
    }

    public Product(String name, String details, String description, Category category, User user) {
        this.name = name;
        this.details = details;
        this.description = description;
        this.category = category;
        this.user = user;
    }

    public Product(int stock, double cost, String imgUrl, Category category, String name,
        String details, String description, User user) {
        this.stock = stock;
        this.cost = cost;
        this.imgUrl = imgUrl;
        this.category = category;
        this.name = name;
        this.details = details;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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
