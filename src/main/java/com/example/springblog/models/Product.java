package com.example.springblog.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {


    @Id @GeneratedValue
    private int id;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private int stock;

    @Column(nullable = true)
    private double cost;

    @Column(name = "img_url", nullable = true)
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Category category; // Not sure if this is correct

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "products_orders",
//            joinColumns = {@JoinColumn(name = "product_id")},
//            inverseJoinColumns = {@JoinColumn(name = "order_id")}
//    )
//    private List<Order> orders;


    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;


    public Product() {
    }

    public Product(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public Product(String name, int stock, double cost, String imgUrl, Category category, String title, String body, User user) {
        this.name = name;
        this.stock = stock;
        this.cost = cost;
        this.imgUrl = imgUrl;
        this.category = category;
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
