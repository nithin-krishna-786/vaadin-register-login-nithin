package com.nithin.vaadinregisterloginnithin.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nithin.vaadinregisterloginnithin.entity.User;
import com.nithin.vaadinregisterloginnithin.events.RegistrationEvent;
import com.nithin.vaadinregisterloginnithin.service.UserService;
import com.nithin.vaadinregisterloginnithin.views.RegistrationView;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;

@Component
public class RegistrationPresenter {
	
//	@Autowired
//	private RegistrationView view;
	
	@Autowired
	private UserService userService;

	public RegistrationPresenter(UserService userService) {
		this.userService = userService;
//		view.setPresenter(this);
	}

	public void onEvent(RegistrationEvent event) {
		switch (event.getType()) {
		case SUBMIT:
			registerUser(event.getUser());
			break;
		}
	}

	private void registerUser(User user) {
		user = userService.createUser(user);
//		view.showNotification("Registration successful");
		Notification.show("Registration successful!", 3000, Position.BOTTOM_START);
	}

}
