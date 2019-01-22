package com.example.springblog.models;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "First name can not be blank")
    @Size(min = 3, message = "First name is required")
    @Column(nullable = false, length = 100)
    private String first_name;

    @NotBlank(message = "Last name can not be blank")
    @Size(min = 3, message = "Last name is required")
    @Column(nullable = false, length = 100)
    private String last_name;

    @Column(nullable = false, length = 1000)
    private String address1;

    @Column(nullable = false, length = 1000)
    private String address2;

    @Column(nullable = false, length = 500)
    private String city;

    @Column(nullable = false, length = 2)
    private String state;

    @Column(nullable = false)
    private String postalCode;

    @NotBlank(message = "Email can not be blank")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email must be formatted correctly")
    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @NotBlank(message = "Password can not be blank")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$",
        message = "Password must have one uppercase letter, one number, and one special "
            + "character")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Column(nullable = false, length = 100)
    private String password;

    @NotBlank(message = "Phone number can not be blank")
    @Size(min = 10, message = "Phone number required")
    @Column(nullable = false)
    private String phone_number;

    @NotBlank(message = "Date of birth can not be blank")
    @Column(nullable = false)
    private long dob;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Order> orders;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String first_name, String last_name, String address1, String address2,
        String city, String state, String postalCode, String email, String username,
        String password, String phone_number, long dob) {
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
    }

    public User(User copy) {
        id = copy.id;
        first_name = copy.first_name;
        last_name = copy.last_name;
        address1 = copy.address1;
        address2 = copy.address2;
        city = copy.city;
        state = copy.state;
        postalCode = copy.postalCode;
        email = copy.email;
        username = copy.username;
        password = copy.password;
        phone_number = copy.phone_number;
        dob = copy.dob;
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

    public long getDob() {
        return dob;
    }

    public void setDob(long dob) {
        this.dob = dob;
    }
}
