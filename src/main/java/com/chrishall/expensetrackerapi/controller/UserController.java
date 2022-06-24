package com.chrishall.expensetrackerapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chrishall.expensetrackerapi.entity.User;
import com.chrishall.expensetrackerapi.entity.UserModel;
import com.chrishall.expensetrackerapi.exceptions.ResourceNotFoundException;
import com.chrishall.expensetrackerapi.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;


	@GetMapping("/profile")
	public ResponseEntity<User> get() {
		return new ResponseEntity<User>(userService.read(), HttpStatus.OK);
	}

	@PutMapping("/profile")
	public ResponseEntity<User> update(@RequestBody User user) {

		return new ResponseEntity<User>(userService.update(user), HttpStatus.OK);
	}

	@DeleteMapping("/deactivate")
	public ResponseEntity<HttpStatus> delete() throws ResourceNotFoundException {
		userService.delete();
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
}
