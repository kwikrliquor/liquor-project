package com.example.springblog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue
    private long id;

    @Column(nullable = false)
    private long date;
    // Potential Alternative code for "date"
    //@Column(nullable = false)
    //private java.sql.Date date;


    // Foreign key order_status_id to order_status
    //@ManyToOne ?


    // Foreign key user_id to users
    //@ManyToOne ?


    @ManyToMany(mappedBy = "orders")
    private List<Product> products;

    // Constructor
    public Order() {
    }

    // Constructor
    public Order(long date, List<Product> products) {
        this.date = date;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
