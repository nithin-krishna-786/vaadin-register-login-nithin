package com.nithin.vaadinregisterloginnithin.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nithin.vaadinregisterloginnithin.entity.User;
import com.nithin.vaadinregisterloginnithin.events.LogInEvent;
import com.nithin.vaadinregisterloginnithin.events.RegistrationEvent;
import com.nithin.vaadinregisterloginnithin.service.UserService;
import com.nithin.vaadinregisterloginnithin.views.RegistrationView;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;

@Component
public class LoginPresenter {
	
//	@Autowired
//	private RegistrationView view;
	
	@Autowired
	private UserService userService;

	public LoginPresenter(UserService userService) {
		this.userService = userService;
//		view.setPresenter(this);
	}

	public void onEvent(LogInEvent event) {
		switch (event.getType()) {
		case LOGIN:
			loginUser(event.getUsername(),event.getPassword());
			break;
		}
	}

	private void loginUser(String username,String password) {
//		User user = userService.logIn(username,password);
		
		
		Notification.show("Logged in successfully", 3000, Position.BOTTOM_START);
	}

}
