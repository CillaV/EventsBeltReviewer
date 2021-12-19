package com.pv.eventsbeltreviewer.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pv.eventsbeltreviewer.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> findAll();
	
	// to find by email needs to take in an email in parameters - @requestParam email when loggin in
	User findByEmail(String email);
	

	

	

}
