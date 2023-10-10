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
	
	@RequestMapping(method=RequestMethod.POST)
	//@PostMapping("/users")
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO){
		User obj = service.fromDTO(objDTO);
		obj= service.insert(obj);
		URI uri =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	//@GetMapping("/users")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	//@PostMapping("/users")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDTO,@PathVariable String id){
		User obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj= service.update(obj);
		return ResponseEntity.noContent().build();
		
		
		
	}
	
	
	
	/*@GetMapping(value="/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
	    Optional<User> user = service.findById(id);
	    
	    return user.map(value -> ResponseEntity.ok().body(new UserDTO(value)))
	               .orElse(ResponseEntity.notFound().build());
	}*/

}
