package com.example.springblog.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

  @Id @GeneratedValue
  private long id;

//  @NotBlank(message = "First name can not be blank")
  @Size(min = 3, message = "First name is required")
  @Column(nullable = true, length = 100)
  private String first_name;

//  @NotBlank(message = "Last name can not be blank")
  @Size(min = 3, message = "Last name is required")
  @Column(nullable = true, length = 100)
  private String last_name;

  @Column(nullable = true, length = 1000)
  private String address1;

  @Column(nullable = true, length = 1000)
  private String address2;

  @Column(nullable = true, length = 500)
  private String city;

  @Column(nullable = true, length = 2)
  private String state;

  @Column(nullable = true)
  private String postalCode;

//  @NotBlank(message = "Email can not be blank")
  @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email not formatted correctly")
  @Column(nullable = false, length = 100, unique = true)
  private String email;

  @Column(nullable = false, length = 100, unique = true)
  private String username;

  @NotBlank(message = "Password can not be blank")
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$",
      message = "Must have one uppercase letter, one number, and one special "
          + "character")
  @Size(min = 8, message = "Password must be at least 8 characters")
  @Column(nullable = false, length = 100)
  private String password;

//  @NotBlank(message = "Phone number can not be blank")
  @Size(min = 10, message = "Phone number required")
  @Column(nullable = true)
  private String phone_number;

//  @NotBlank(message = "Date of birth can not be blank")
  @Column(nullable = true)
  private String dob;

  @Column(nullable = true, length = 2000)
  private String img_url;

  @Column(nullable=true)
  private Boolean ageVerified;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
  private List<Order> orders;

  public User() {
  }

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public User(String username, String email, String password, String img_url, Boolean ageVerified) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.img_url = img_url;
    this.ageVerified = ageVerified;
  }



  public User(String first_name, String last_name, String address1,
              String address2, String city, String state, String postalCode,
              String email, String username, String password, String phone_number,
              String dob, String img_url, Boolean ageVerified) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.address1 = address1;
    this.address2 = address2;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
    this.email = email;
    this.username = username;
    this.password = password;
    this.phone_number = phone_number;
    this.dob = dob;
    this.img_url = img_url;
    this.ageVerified = ageVerified;
  }

  public User(String first_name, String last_name, String address1,
              String address2, String city, String state, String postalCode,
              String email, String username, String phone_number, String dob) {
    this.username = username;
    this.email = email;
    this.first_name = first_name;
    this.last_name = last_name;
    this.address1 = address1;
    this.address2 = address2;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
    this.phone_number = phone_number;
    this.dob = dob;
  }

  public User(User copy) {
    id = copy.id;
    email = copy.email;
    username = copy.username;
    password = copy.password;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhone_number() {
    return phone_number;
  }

  public void setPhone_number(String phone_number) {
    this.phone_number = phone_number;
  }

  public String getDob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public String getImg_url() {
    return img_url;
  }

  public void setImg_url(String img_url) {
    this.img_url = img_url;
  }

  public Boolean getAgeVerified() {
    return ageVerified;
  }

  public void setAgeVerified(Boolean ageVerified) {
    this.ageVerified = ageVerified;
  }

}
