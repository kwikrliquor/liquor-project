package com.example.springblog.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

  @ManyToOne
  @JoinColumn (name = "order_status_id")
  private OrderStatus orderStatus;

  @ManyToOne
  @JoinColumn (name = "user_id")
  private User user;

  public Order() {
  }

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
