package com.uniovi.services;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Product;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RolesService rolesService;


	@PostConstruct
	public void init() {
		User user1 = new User("pedro@pedro", "Pedro", "Díaz");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		
		User user2 = new User("lucas@lucas", "Lucas", "Núñez");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[1]);
		
		User user3 = new User("maria@maria", "María", "Rodríguez");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		
		User user4 = new User("marta@marta", "Marta", "Almonte");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[1]);
		
		User user5 = new User("pelayo@pelayo", "Pelayo", "Valdes");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[0]);
		
		User user6 = new User("edward@edward", "Edward", "Núñez");
		user6.setPassword("123456");
		user6.setRole(rolesService.getRoles()[1]);
		
		Set<Product> user1Desc = new HashSet<Product>() {
			{
				add(new Product("Descripción A1", 10.0, user1));
				add(new Product("Descripción A2", 9.0, user1));
				add(new Product("Descripción A3", 7.0, user1));
				add(new Product("Descripción A4", 6.5, user1));
			}
		};
		user1.setProducts(user1Desc);
		
		Set<Product> user2Desc = new HashSet<Product>() {
			{
				add(new Product("Descripción B1", 5.0, user2));
				add(new Product("Descripción B2", 4.3, user2));
				add(new Product("Descripción B3", 8.0, user2));
				add(new Product("Descripción B4", 3.5, user2));
			}
		};
		user2.setProducts(user2Desc);
		
		Set<Product> user3Desc = new HashSet<Product>() {
			{
				;
				add(new Product("Descripción C1", 5.5, user3));
				add(new Product("Descripción C2", 6.6, user3));
				add(new Product("Descripción C3", 7.0, user3));
			}
		};
		user3.setProducts(user3Desc);
		
		Set<Product> user4Desc = new HashSet<Product>() {
			{
				add(new Product("Descripción D1", 10.0, user4));
				add(new Product("Descripción D2", 8.0, user4));
				add(new Product("Descripción D3", 9.0, user4));
			}
		};
		user4.setProducts(user4Desc);
		
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
	}
}