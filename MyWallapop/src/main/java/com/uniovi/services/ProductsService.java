package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Product;
import com.uniovi.repositories.ProductsRepository;

@Service
public class ProductsService {
	
	
	@Autowired
	private ProductsRepository productsRepository; 

	/**
	 * Obtener todos los productos
	 * @return
	 */
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		productsRepository.findAll().forEach(products::add);
		return products;
	}

	/**
	 * Obtener un producto específico por id
	 * @param id
	 * @return
	 */
	public Product getProduct(Long id) {
		return productsRepository.findById(id).get();
	}

	/**
	 * Añadir un producto
	 * @param product
	 */
	public void addProduct(Product product) {
		productsRepository.save(product);
	}

	
	/**
	 * Eliminar un producto insertando su id
	 * @param id
	 */
	public void deleteProduct(Long id) {
		productsRepository.deleteById(id);
	}
}