package com.padilha.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.padilha.workshopmongo.domain.Post;
import com.padilha.workshopmongo.domain.User;
import com.padilha.workshopmongo.dto.UserDTO;
import com.padilha.workshopmongo.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

	@Autowired
	private PostService service;
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	//@GetMapping("/users")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	

}
