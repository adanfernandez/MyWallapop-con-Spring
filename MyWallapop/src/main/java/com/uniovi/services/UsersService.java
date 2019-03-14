package com.uniovi.services;

import java.util.*;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	public void init() {
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}

	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	public void addUser(User user) {
		if (usersRepository.findByEmail(user.getEmail()) == null) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setMoney(100);
		}
		usersRepository.save(user);
	}

	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	public void deleteUser(List<Long> ids, User user) {
		if (ids != null) {
			if (!ids.contains(user.getId())) {
				for (Long id : ids)
					usersRepository.deleteById(id);
			}
		}
	}

	

	public List<User> findUsersMinusPrincipal(User user) {
		List<User> userList = this.getUsers();
		userList.remove(user);
		return userList;
	}

	public void substractMoney(User user, Double price) {
		usersRepository.substractMoney(user.getId(), price);
	}
	
	public void addMoney(User user, Double price) {
		usersRepository.addMoney(user.getId(), price);
	}
}
