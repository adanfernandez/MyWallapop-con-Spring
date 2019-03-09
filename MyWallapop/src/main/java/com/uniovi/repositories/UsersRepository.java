package com.uniovi.repositories;

import com.uniovi.entities.*;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String email);

	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.money = (u.money - ?2) WHERE u.id = ?1")
	void updateMoney(Long id, Double price);

}