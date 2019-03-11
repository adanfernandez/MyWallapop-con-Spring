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

	public void deleteUser(ArrayList<Long> ids, User user) {
		if(!ids.contains(user.getId()))
		{
			for (Long id : ids)
				usersRepository.deleteById(id);
		}
	}

	public void updateMoney(User user, Double price) {
		usersRepository.updateMoney(user.getId(), price);
	}
	
	public List<User> findUsersMinusPrincipal(User user)
	{
		List<User> userList =  this.getUsers();
		userList.remove(user);
		return userList;
	}
}
