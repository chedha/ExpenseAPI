package com.chrishall.expensetrackerapi.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserModel {
	
	@NotBlank(message = "Name field cannot be blank")
	private String name;
	
	@NotNull(message = "Name field cannot be blank")
	@Email(message = "Valid email should be entered")
	private String email;
	
	@NotNull(message = "Password should not be empty")
	@Size(min = 5, message = "Password should be at least 5 characters")
	private String password;
	
	private Long age = 0L;

}
