package com.chrishall.expensetrackerapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chrishall.expensetrackerapi.entity.User;
import com.chrishall.expensetrackerapi.entity.UserModel;
import com.chrishall.expensetrackerapi.exceptions.ItemAlreadyExistsException;
import com.chrishall.expensetrackerapi.exceptions.ResourceNotFoundException;
import com.chrishall.expensetrackerapi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private UserRepository userRepo;

	@Override
	public User createUser(UserModel user) {
		if (userRepo.existsByEmail(user.getEmail())) {
			throw new ItemAlreadyExistsException("User is already registered with email: " + user.getEmail());
		}

		User newUser = new User();
		BeanUtils.copyProperties(user, newUser);
		newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
		return userRepo.save(newUser);

	}

	@Override
	public User read() throws ResourceNotFoundException {
		
		Long userId = getLoggedInUser().getId();

		return userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for the id: " + userId));
	}

	@Override
	public User update(User user) throws ResourceNotFoundException {
		User oUser = read();
		oUser.setName(user.getName() != null ? user.getName() : oUser.getName());
		oUser.setEmail(user.getEmail() != null ? user.getEmail() : oUser.getEmail());
		oUser.setPassword(user.getPassword() != null ? bcryptEncoder.encode(user.getPassword()) : oUser.getPassword());
		oUser.setAge(user.getAge() != null ? user.getAge() : oUser.getAge());
		return userRepo.save(oUser);
	}

	@Override
	public void delete() {
		User user = read();
		userRepo.delete(user);
		
	}

	@Override
	public User getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		
		return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found for the email" + email));
		
		
	}

}
