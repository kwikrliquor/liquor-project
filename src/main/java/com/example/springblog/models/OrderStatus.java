package com.example.springblog.models;

import javax.persistence.*;

@Entity
@Table(name = "order_status")
public class OrderStatus {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String status;

	public OrderStatus() {
	}

	public OrderStatus(String status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
