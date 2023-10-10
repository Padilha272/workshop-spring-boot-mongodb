package com.padilha.workshopmongo.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.padilha.workshopmongo.domain.User;
import com.padilha.workshopmongo.dto.UserDTO;
import com.padilha.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService service;
	@RequestMapping(method=RequestMethod.GET)
	//@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x->new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
		
	}
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	//@GetMapping("/users")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User user = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
		
	}
	
	
	
	
	
	
	/*@GetMapping(value="/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
	    Optional<User> user = service.findById(id);
	    
	    return user.map(value -> ResponseEntity.ok().body(new UserDTO(value)))
	               .orElse(ResponseEntity.notFound().build());
	}*/

}