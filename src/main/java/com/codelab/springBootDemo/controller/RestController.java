package com.codelab.springBootDemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.codelab.springBootDemo.SpringBootDemoApplication;
import com.codelab.springBootDemo.entity.Post;
import com.codelab.springBootDemo.entity.Comment;
import com.codelab.springBootDemo.entity.User;
import com.codelab.springBootDemo.service.CommentService;
import com.codelab.springBootDemo.service.PostService;
import com.codelab.springBootDemo.service.UserService;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootDemoApplication.class);

	@Autowired
	UserService userService;

	@Autowired
	CommentService commentService ;
	
	@Autowired
	PostService postservice;
	
	@GetMapping("/health")
	public String health() {
		return "Welcome to  Example.";
	}

	@PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder ucBuilder) {

		LOGGER.debug("Creating User ***********************", user);
		// System.out.println("Creating User "+user.getName());
		userService.createUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateUser(@RequestBody User currentUser) {
		System.out.println("sd");
		User user = userService.findById(currentUser.getId());
		if (user == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		userService.update(currentUser, currentUser.getId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PostMapping(path = "/addpost",consumes = "application/json", produces = "application/json")
	public ResponseEntity<Void> createPost( @RequestBody Post post) {
		postservice.createPost(post);
		return  new ResponseEntity<Void>(HttpStatus.CREATED);
		
	}
	@PutMapping(path = "/addcomment/{postid}",consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> addComment( @RequestBody Comment comment,@PathVariable(name="postid") long postid) {
		LOGGER.info("adding comment"+comment);
		Post post = postservice.findPostById(postid);
		//post.getComments().add(comment);
		//postservice.createPost(post);
		//commentService.addCommnet(comment);
		postservice.addComment(postid, comment);
		return  new ResponseEntity<String>("comment added",HttpStatus.CREATED);
		
	}
	@GetMapping(path = "/getcomment/{title}", produces = "application/json")
	public ResponseEntity<List> getcommentByTitle( @PathVariable(name="title") String title) {
		List list = commentService.getCommentByTitle(title);
		return new ResponseEntity<List>(list,HttpStatus.OK);
	}

	@PutMapping(path="/updatecomment/{postid}",consumes = "application/json")
	public ResponseEntity<Void> updateComment(@PathVariable(name="postid")int postid,@RequestBody String message){
		JSONObject obj;String msg = null;
		try {
			JSONObject m = new JSONObject(message);
			msg = m.getString("message");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		commentService.updateComment(postid,msg);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	

		
}
