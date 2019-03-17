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
		user2.setRole(rolesService.getRoles()[0]);
		
		User user3 = new User("maria@maria", "María", "Rodríguez");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		
		User user4 = new User("marta@marta", "Marta", "Almonte");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		
		User user5 = new User("pelayo@pelayo", "Pelayo", "Valdes");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[0]);
		
		User user6 = new User("admin@admin", "Edward", "Núñez");
		user6.setPassword("admin");
		user6.setRole(rolesService.getRoles()[1]);
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		
		
		Set<Product> user1Desc = new HashSet<Product>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Product("Titulo A1", "Descripción A1", 101.0, user1));
				add(new Product("Titulo A2","Descripción A2", 9.0, user1));
				add(new Product("Titulo A3","Descripción A3", 7.0, user1));
				add(new Product("Titulo A4","Descripción A4", 6.5, user1));
			}
		};
		
		Set<Product> user2Desc = new HashSet<Product>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Product("Titulo B1","Descripción B1", 5.0, user2));
				add(new Product("Titulo B2","Descripción B2", 4.3, user2));
				add(new Product("Titulo B3","Descripción B3", 8.0, user2));
				add(new Product("Titulo B4","Descripción B4", 3.5, user2));
			}
		};
		
		
		
		Set<Product> user3Desc = new HashSet<Product>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				;
				add(new Product("Titulo C1","Descripción C1", 5.5, user3));
				add(new Product("Titulo C2","Descripción C2", 6.6, user3));
				add(new Product("Titulo C3","Descripción C3", 7.0, user3));
			}
		};
		
		Set<Product> user4Desc = new HashSet<Product>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Product("Titulo D1","Descripción D1", 10.0, user4));
				add(new Product("Titulo D2","Descripción D2", 8.0, user4));
				add(new Product("Titulo D3","Descripción D3", 9.0, user4));
			}
		};
		
		
		Set<Product> user5Desc = new HashSet<Product>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Product("Titulo E1","Descripción E1", 13.0, user5));
				add(new Product("Titulo E2","Descripción E2", 12.0, user5));
				add(new Product("Titulo E3","Descripción E3", 11.0, user5));
			}
		};
		
		
		
		
		
		/*Set<Product> user1Purchaseds = new HashSet<Product>();
		Set<Product> user2Purchaseds = new HashSet<Product>();
		Set<Product> user3Purchaseds = new HashSet<Product>();
		Set<Product> user4Purchaseds = new HashSet<Product>();
		Set<Product> user5Purchaseds = new HashSet<Product>();
		*/
		
		
		Product product1 = new Product("Titulo F1","Descripción F1", 14.0, user1, user2);
		user1Desc.add(product1);
		//user2Purchaseds.add(product1);
		
		Product product2 = new Product("Titulo G1","Descripción G1", 15.0, user1, user3);
		user1Desc.add(product2);
		//user3Purchaseds.add(product2);
		
		Product product3 = new Product("Titulo H1","Descripción H1", 16.0, user2, user4);
		user2Desc.add(product3);
		//user4Purchaseds.add(product3);
		
		Product product4 = new Product("Titulo I1","Descripción I1", 17.0, user2, user5);
		user2Desc.add(product4);
	//	user5Purchaseds.add(product4);
		
		Product product5 = new Product("Titulo J1","Descripción J1", 18.0, user3, user1);
		user3Desc.add(product5);
	//	user1Purchaseds.add(product5);
		
		Product product6 = new Product("Titulo K1","Descripción K1", 19.0, user3, user2);
		user3Desc.add(product6);
	//	user2Purchaseds.add(product6);
		
		Product product7 = new Product("Titulo L1","Descripción L1", 20.0, user4, user3);
		user4Desc.add(product7);
	//	user3Purchaseds.add(product7);
		
		Product product8 = new Product("Titulo M1","Descripción M1", 21.0, user4, user5);
		user4Desc.add(product8);
	//	user5Purchaseds.add(product8);
		
		Product product9 = new Product("Titulo N1","Descripción N1", 22.0, user5, user4);
		user5Desc.add(product9);
	//	user4Purchaseds.add(product9);

		Product product10 = new Product("Titulo O1","Descripción O1", 23.0, user5, user1);
		user5Desc.add(product10);
	//	user1Purchaseds.add(product10);
		
		
		user1.setProducts(user1Desc);
		user2.setProducts(user2Desc);
		user3.setProducts(user3Desc);
		user4.setProducts(user4Desc);
		user5.setProducts(user5Desc);
		
//		user1.setPurchaseds(user1Purchaseds);
//		user2.setPurchaseds(user2Purchaseds);
//		user3.setPurchaseds(user3Purchaseds);
//		user4.setPurchaseds(user4Purchaseds);
//		user5.setPurchaseds(user5Purchaseds);
		
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		
		
		
		
		
		/*User user1 = new User("pedro@pedro", "Pedro", "Gracia");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		
		User user2 = new User("lucas@lucas", "Lucas", "Martinez");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);
		
		User user3 = new User("xurde@lluis", "xurde", "Lluis");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		
		User user4 = new User("diego@diego", "Diego", "Cervero");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		
		User user5 = new User("xuan@xuan", "Xuan", "Zapico");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[0]);
		
		User user6 = new User("admin@admin", "admin", "admin");
		user6.setPassword("admin");
		user6.setRole(rolesService.getRoles()[1]);
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		
		Set<Product> user1Products = new HashSet<Product>();
		Set<Product> user2Products = new HashSet<Product>();
		Set<Product> user3Products = new HashSet<Product>();
		Set<Product> user4Products = new HashSet<Product>();
		Set<Product> user5Products = new HashSet<Product>();
		
		Set<Product> user1Purchaseds = new HashSet<Product>();
		Set<Product> user2Purchaseds = new HashSet<Product>();
		Set<Product> user3Purchaseds = new HashSet<Product>();
		Set<Product> user4Purchaseds = new HashSet<Product>();
		Set<Product> user5Purchaseds = new HashSet<Product>();
		
		Product product1 = new Product("Oferta 1 del usuario1", "Descripcion del usuario", 15.0, user1);
		product1.setBuyer(user2);
		user1Products.add(product1);
		user2Purchaseds.add(product1);
		
		Product product2 = new Product("Oferta 2 del usuario1", "Descripcion del usuario", 15.0, user1);
		product2.setBuyer(user3);
		user1Products.add(product2);
		user3Purchaseds.add(product2);
		
		Product product3 = new Product("Oferta 3 del usuario1", "Descripcion del usuario", 15.0, user1);
		product3.setBuyer(user4);
		user1Products.add(product3);
		user4Purchaseds.add(product3);
		
		Product product4 = new Product("Oferta 1 del usuario2", "Descripcion del usuario", 15.0, user2);
		product4.setBuyer(user5);
		user2Products.add(product4);
		user5Purchaseds.add(product4);
		
		Product product5 = new Product("Oferta 2 del usuario2", "Descripcion del usuario", 15.0, user2);
		product5.setBuyer(user1);
		user2Products.add(product5);
		user1Purchaseds.add(product5);
		
		Product product6 = new Product("Oferta 3 del usuario2", "Descripcion del usuario", 15.0, user2);
		product6.setBuyer(user3);
		user2Products.add(product6);
		user2Purchaseds.add(product6);
		
		Product product7 = new Product("Oferta 1 del usuario3", "Descripcion del usuario", 15.0, user3);
		product7.setBuyer(user2);
		user3Products.add(product7);
		user3Purchaseds.add(product7);
	
		Product product8 = new Product("Oferta 2 del usuario3", "Descripcion del usuario", 15.0, user3);
		product8.setBuyer(user4);
		user3Products.add(product8);
		user4Purchaseds.add(product8);
		
		Product product9 = new Product("Oferta 3 del usuario3", "Descripcion del usuario", 15.0, user3);
		product9.setBuyer(user5);
		user3Products.add(product9);
		user5Purchaseds.add(product9);
		
		Product product10 = new Product("Oferta 1 del usuario4", "Descripcion del usuario", 15.0, user4);
		product10.setBuyer(user1);
		user4Products.add(product10);
		
		Product product11 = new Product("Oferta 2 del usuario4", "Descripcion del usuario", 15.0, user4);
		user4Products.add(product11);
		
		Product product12 = new Product("Oferta 3 del usuario4", "Descripcion del usuario", 15.0, user4);
		user4Products.add(product12);
		
		Product product13 = new Product("Oferta 1 del usuario5", "Descripcion del usuario", 15.0, user5);
		user5Products.add(product13);
		
		Product product14 = new Product("Oferta 2 del usuario5", "Descripcion del usuario", 15.0, user5);
		user5Products.add(product14);
		
		Product product15 = new Product("Oferta 3 del usuario5", "Descripcion del usuario", 15.0, user5);
		user5Products.add(product15);
		
		user1.setProducts(user1Products);
		user1.setPurchaseds(user1Purchaseds);
		
		user2.setProducts(user2Products);
		user2.setPurchaseds(user2Purchaseds);
		
		user3.setProducts(user3Products);
		user3.setPurchaseds(user3Purchaseds);
		
		user4.setProducts(user4Products);
		user4.setPurchaseds(user4Purchaseds);
		
		user5.setProducts(user5Products);
		user5.setPurchaseds(user5Purchaseds);

		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
*/
		
	}
}