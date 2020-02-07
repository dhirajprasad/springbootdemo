package com.codelab.springBootDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.codelab.springBootDemo.entity.User;
import com.codelab.springBootDemo.service.UserService;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	UserService userService;
	
	@GetMapping("/health")
    public String health() {
        return "Welcome to  Example.";
    }
	@PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Void> createUser(@RequestBody User user,UriComponentsBuilder ucBuilder){
		 System.out.println("Creating User "+user.getName());
	        userService.createUser(user);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	 @PutMapping(value="/update", headers="Accept=application/json")
	    public ResponseEntity<String> updateUser(@RequestBody User currentUser)
	    {
	        System.out.println("sd");
	        User user = userService.findById(currentUser.getId());
	        if (user==null) {
	            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	        }
	        userService.update(currentUser, currentUser.getId());
	        return new ResponseEntity<String>(HttpStatus.OK);
	    }
}
