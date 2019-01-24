package com.example.springblog.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRole {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "role")
  private String role;

  @Column(name = "user_id")
  private long userId;

  public UserRole() {
  }

  public UserRole(String role, long userId) {
    this.role = role;
    this.userId = userId;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

}