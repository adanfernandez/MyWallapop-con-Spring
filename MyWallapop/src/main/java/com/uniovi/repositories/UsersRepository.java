package com.uniovi.repositories;

import com.uniovi.entities.*;

import java.util.List;

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
	
/*	//@Query("SELECT u FROM User where u.id != (?1)")
	@Query("SELECT u FROM USER")
	List<User> findUsersMinusPrincipal(Long id);
*/
}