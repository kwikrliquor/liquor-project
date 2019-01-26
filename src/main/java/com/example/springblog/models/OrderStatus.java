package com.example.springblog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_status")
public class OrderStatus {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String status;

	public OrderStatus() {
	}

	public OrderStatus(String status) {
		this.status = status;
	}

	public int getId() { return id; }

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
