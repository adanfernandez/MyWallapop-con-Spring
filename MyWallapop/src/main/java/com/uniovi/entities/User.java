package com.uniovi.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;
	@Column(unique = true)
	private String email;
	private String name;
	private String lastName;
	private String role;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Product> products;
	private double money;

	@OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
	private Set<Product> purchaseds;

	private String password;
	@Transient // propiedad que no se almacena e la tabla.
	private String passwordConfirm;

	public User(String email, String name, String lastName) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
	}

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public String getFullName() {
		return this.name + " " + this.lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Product> getPurchaseds() {
		return purchaseds;
	}

	public void setPurchaseds(Set<Product> purchaseds) {
		this.purchaseds = purchaseds;
	}

	@Override
	public String toString() {
		return "User [name=" + name	+ ", money=" + money + ", purchaseds=" + purchaseds + "]";
	}
}
