package com.padilha.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padilha.workshopmongo.domain.User;
import com.padilha.workshopmongo.repository.UserRepository;
import com.padilha.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional <User>user = repo.findById(id);
		if (!user.isPresent()) {
			throw new ObjectNotFoundException("Object not found");
		}
		return user.get();
	}
}
