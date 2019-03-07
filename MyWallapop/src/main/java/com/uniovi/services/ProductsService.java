package com.uniovi.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Product;
import com.uniovi.repositories.ProductsRepository;

@Service
public class ProductsService {

	@Autowired
	private ProductsRepository productsRepository;

	@Autowired
	private HttpSession httpSession;

	/**
	 * Obtener todos los productos
	 * 
	 * @return
	 */
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		productsRepository.findAll().forEach(products::add);
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
	public void addProduct(Product product) {
		productsRepository.save(product);
	}

	/**
	 * Eliminar un producto insertando su id
	 * 
	 * @param id
	 */
	public void deleteProduct(Long id) {
		productsRepository.deleteById(id);
	}
}