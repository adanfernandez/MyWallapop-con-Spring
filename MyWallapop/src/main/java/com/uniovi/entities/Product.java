package com.uniovi.entities;

import java.util.Date;

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
	private String title;
	private String description;
	private Double price;
	private Date date = new Date();

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Product(String title, String description, Double price) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
	}

	public Product(String title, String description, Double price, User user) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.user = user;
	}

	public Product() {
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", date=" + date + ", user=" + user.getEmail() + "]";
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
	
	public Date getDate()
	{
		return date;
	}
	
	public void setDate(Date date)
	{
		this.date = date;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}

}