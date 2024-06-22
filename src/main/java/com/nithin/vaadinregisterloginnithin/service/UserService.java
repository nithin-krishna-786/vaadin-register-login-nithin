package com.nithin.vaadinregisterloginnithin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nithin.vaadinregisterloginnithin.entity.User;
import com.nithin.vaadinregisterloginnithin.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User user) {
		System.out.println("User created:"+user);
		user = userRepository.save(user);
		return user;
	}

}
