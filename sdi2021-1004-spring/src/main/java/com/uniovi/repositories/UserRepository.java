package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByDni(String dni);
	
	@Query("SELECT r from User r WHERE (LOWER(r.name) LIKE LOWER(?1) or LOWER(r.lastName) LIKE LOWER(?1))")
	Page<User> searchByNameAndLastname(Pageable p, String str);

	Page<User> findAll(Pageable p);
}
