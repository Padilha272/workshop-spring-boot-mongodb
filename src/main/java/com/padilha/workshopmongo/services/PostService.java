package com.padilha.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padilha.workshopmongo.domain.Post;
import com.padilha.workshopmongo.repository.PostRepository;
import com.padilha.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	
	
	public Post findById(String id) {
		Optional <Post>user = repo.findById(id);
		if (!user.isPresent()) {
			throw new ObjectNotFoundException("Object not found");
		}
		return user.get();
	}
	
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate ){
		maxDate = new Date(maxDate.getTime()+24*60*60*1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
	
	
}
