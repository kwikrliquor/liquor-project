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
    @ManyToOne
    @JoinColumn (name = "order_status_id")
    private OrderStatus orderStatus;


    // Foreign key user_id to users
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;


//    @ManyToMany(mappedBy = "orders")
//    private List<Product> products;



    // Constructor
    public Order() {
    }

    // Constructor
    public Order(long date) {
        this.date = date;
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

}
