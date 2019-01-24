package com.example.springblog.models;

import javax.persistence.*;

@Entity
@Table(name = "user_reviews")
public class UserReview {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = true)
	private int rating;

	@Column(nullable = true, length = 1000)
	private String review;

	@ManyToOne
	@JoinColumn (name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn (name = "product_id")
	private Product product;

}
