package com.chrishall.expensetrackerapi.service;

import com.chrishall.expensetrackerapi.entity.User;
import com.chrishall.expensetrackerapi.entity.UserModel;

public interface UserService {
	
	User createUser(UserModel user);
	
	User read();
	
	User update(User user);
	
	void delete();
	
	User getLoggedInUser();

}
