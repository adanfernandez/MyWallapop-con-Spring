package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Product;


public interface ProductsRepository extends CrudRepository<Product, Long>{

}
