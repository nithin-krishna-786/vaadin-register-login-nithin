package com.nithin.vaadinregisterloginnithin.service;

import org.springframework.stereotype.Service;

import com.nithin.vaadinregisterloginnithin.entity.User;

@Service
public class UserService {
	
	public void createUser(User user) {
		System.out.println("User created:"+user);
	}

}
