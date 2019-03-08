package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Product;
import com.uniovi.entities.User;


public interface ProductsRepository extends CrudRepository<Product, Long>{

	List<Product> findAllByUser(User user);
	
	@Query("SELECT r FROM Product r WHERE (LOWER(r.title) LIKE LOWER(?1))")
	List<Product> searchByTitle(String seachtext);
	
}
