package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Mark;
import com.uniovi.entities.User;
import com.uniovi.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository usersRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Page<User> getUsers(Pageable p) {
		Page<User> users = usersRepository.findAll(p);
		return users;
	}
	
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}

	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}
	
	public User getUserByDni(String dni) {
		return usersRepository.findByDni(dni);
		}

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}
	
	public Page<User> searchUsersByNameAndLastname (Pageable p, String searchText){
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		searchText = "%"+searchText+"%";
		users = usersRepository.searchByNameAndLastname(p, searchText);
		return users;
	}
	
}
