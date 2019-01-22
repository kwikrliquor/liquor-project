package com.example.springblog.models;

import javax.persistence.*;

@Entity
@Table(name = "user_reviews")
public class UserReview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;


}
