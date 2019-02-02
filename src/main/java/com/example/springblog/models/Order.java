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
  private String date;

  @Column(nullable = true)
  private String tempAddress;
  // Potential Alternative code for "date"
  //@Column(nullable = false)
  //private java.sql.Date date;

  @ManyToOne
  @JoinColumn (name = "order_status_id")
  private OrderStatus orderStatusId;

  @ManyToOne
  @JoinColumn (name = "user_id")
  private User userId;

  public Order() {
  }

  public Order(String date) {
    this.date = date;
  }

  public Order(String date, String tempAddress, OrderStatus orderStatusId, User userId) {
    this.date = date;
    this.tempAddress = tempAddress;
    this.orderStatusId = orderStatusId;
    this.userId = userId;
  }

  public Order(String date, OrderStatus orderStatusId, User userId) {
    this.date = date;
    this.orderStatusId = orderStatusId;
    this.userId = userId;
  }

  public Order(String date, User userId) {
    this.date = date;
    this.userId = userId;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public OrderStatus getOrderStatusId() {
    return orderStatusId;
  }

  public void setOrderStatusId(OrderStatus orderStatusId) {
    this.orderStatusId = orderStatusId;
  }

  public User getUserId() {
    return userId;
  }

  public void setUserId(User userId) {
    this.userId = userId;
  }

  public String getTempAddress() {
    return tempAddress;
  }

  public void setTempAddress(String tempAddress) {
    this.tempAddress = tempAddress;
  }

}
