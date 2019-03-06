package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private Long id;
	private String description;
	private Double price;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Product(String description, Double price) {
		super();
		this.description = description;
		this.price = price;
	}

	public Product(String description, Double price, User user) {
		super();
		this.description = description;
		this.price = price;
		this.user = user;
	}

	public Product() {
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", description=" + description + ", price=" + price + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}