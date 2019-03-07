package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Product;
import com.uniovi.entities.User;


public interface ProductsRepository extends CrudRepository<Product, Long>{

	List<Product> findAllByUser(User user); //pagina 23
	
}
