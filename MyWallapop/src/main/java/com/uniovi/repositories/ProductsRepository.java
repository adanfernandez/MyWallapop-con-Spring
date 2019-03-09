package com.uniovi.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Product;
import com.uniovi.entities.User;


public interface ProductsRepository extends CrudRepository<Product, Long>{

	List<Product> findAllByUser(User user);

	List<Product> findAllByBuyer(User user);
	
	@Query("SELECT r FROM Product r WHERE (LOWER(r.title) LIKE LOWER(?1))")
	List<Product> searchByTitle(String seachtext);
	
	@Modifying
	@Transactional
	@Query("UPDATE Product p SET p.isBuyed = true, p.buyer.id = ?2 WHERE p.id = ?1")
	void buyProduct(Long id_product, Long id_user);
}
