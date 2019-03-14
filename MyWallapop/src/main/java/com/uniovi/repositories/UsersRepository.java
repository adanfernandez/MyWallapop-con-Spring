package com.uniovi.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String email);

	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.money = (u.money - ?2) WHERE u.id = ?1")
	void substractMoney(Long id, Double price);

	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.money = (u.money + ?2) WHERE u.id = ?1")
	void addMoney(Long id, Double price);
	
/*	//@Query("SELECT u FROM User where u.id != (?1)")
	@Query("SELECT u FROM USER")
	List<User> findUsersMinusPrincipal(Long id);
*/
}