package com.example.springblog.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private double cost;

    @Column(name = "img_url", nullable = false)
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private ProductCategory productCategory;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "products_orders",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")}
    )
    private List<ProductOrder> productOrders;


    // Constructor
    public Product() {
    }

    // Constructor
    public Product(String name, int stock, double cost, String imgUrl, ProductCategory productCategory) {
        this.name = name;
        this.stock = stock;
        this.cost = cost;
        this.imgUrl = imgUrl;
        this.productCategory = productCategory;
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

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

}
