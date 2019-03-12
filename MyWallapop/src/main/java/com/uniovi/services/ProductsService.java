package com.uniovi.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Product;
import com.uniovi.entities.User;
import com.uniovi.repositories.ProductsRepository;


@Service
public class ProductsService {

	@Autowired
	private ProductsRepository productsRepository;
	
	@Autowired
	private UsersService usersService;

	@Autowired
	private HttpSession httpSession;

	/**
	 * Obtener todos los productos
	 * 
	 * @return
	 */
	public Page<Product> getProducts(Pageable pageable) {
		Page<Product> products = productsRepository.findAll(pageable);
		return products;
	}

	/**
	 * Obtener un producto específico por id
	 * 
	 * @param id
	 * @return
	 */
	public Product getProduct(Long id) {
		Set<Product> consultedList = (Set<Product>) httpSession.getAttribute("consultedList");
		if (consultedList == null) {
			consultedList = new HashSet<Product>();
		}
		Product productObtained = productsRepository.findById(id).get();
		consultedList.add(productObtained);
		httpSession.setAttribute("consultedList", consultedList);
		return productObtained;
	}

	/**
	 * Añadir un producto
	 * 
	 * @param product
	 */
	public void addProduct(Product product, User user) {
		if (product.getUser().getEmail().equals(user.getEmail()))
			productsRepository.save(product);
	}

	/**
	 * Eliminar un producto insertando su id
	 * 
	 * @param id
	 */
	public void deleteProduct(Long id, User user) {
		List<Product> products = productsRepository.findAllByUser(user);

		/*
		 * Product product = productsRepository.findById(id).orElse(null); if(product ==
		 * null) { if(products.contains(product)) productsRepository.deleteById(id); }
		 * 
		 * for(Product p : productsRepository.findAll()) {
		 * System.out.println(p.toString()); } System.out.println("\n\n\n\n");
		 */

		Optional<Product> product = productsRepository.findById(id);
		product.ifPresent(prod -> {
			if (products.contains(prod)) {
				productsRepository.delete(prod);
			}
		});
	}

	/**
	 * Obtener productos de un usuario específico
	 * 
	 * @param user
	 * @return
	 */
	public List<Product> getProductsForUser(User user) {
		List<Product> products = new ArrayList<Product>();
		products = productsRepository.findAllByUser(user);
		return products;
	}

	/**
	 * Obtener los productos que contengan la cadena de texto pasada por parámetro
	 * @param searchText
	 * @return
	 */
	public Page<Product> searchProductsByTitle(Pageable pageable, String searchText) {
		searchText = "%"+searchText+"%";
		Page<Product> products = new PageImpl<Product>(new LinkedList<Product>());
		products = productsRepository.searchByTitle(pageable, searchText);
		return products;
	}
	
	public void buy(Long id, User user)
	{
		Product product = getProduct(id);
	
		if(!(product.getPrice() > user.getMoney()
				|| product.getUser().equals(user)
				|| product.isBuyed())){
			productsRepository.buyProduct(id, user.getId());
			usersService.updateMoney(user, product.getPrice());
			
/*			for(Product p : productsRepository.findAll())
			{
				System.out.println("LISTA DE PRODUCTOS");
				System.out.println(p);
			}
			System.out.println("\n\n\n");

			for(User u : usersService.getUsers()) {
				System.out.println("LISTA DE USUARIOS");
				System.out.println(u);
			}
			
			System.out.println("\n\n\n\n\n\n\n");
*/		
		}
	}
	
	/**
	 * Obtener productos comprados por un usuario
	 * @param user
	 * @return
	 */
	public List<Product> getProductsPurchased(User user)
	{
		ArrayList<Product> products = (ArrayList<Product>) productsRepository.findAllByBuyer(user);
		return products;
	}
}