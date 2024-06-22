package com.nithin.vaadinregisterloginnithin.views;


import org.springframework.beans.factory.annotation.Autowired;

import com.nithin.vaadinregisterloginnithin.entity.User;
import com.nithin.vaadinregisterloginnithin.presenter.RegistrationPresenter;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route("")
@RouteAlias("login")
public class LoginView extends VerticalLayout {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 8765440703771392684L;
	private TextField username;
	private PasswordField password;
	private Button loginButton;
	private Button registerButton;

	private Binder<User> binder = new Binder<>(User.class);

	@Autowired
	private RegistrationPresenter presenter;

	public LoginView() {

		VerticalLayout layout = new VerticalLayout();
		//MINIMAL SPACING AND PADDDING
		layout.setPadding(false); // Remove padding around the layout
		layout.setSpacing(false); // Remove spacing between components
		
		layout.setSizeFull();
		layout.setAlignItems(Alignment.CENTER);
		layout.setJustifyContentMode(JustifyContentMode.CENTER);

		// Create form fields
		username = new TextField("Username");
		password = new PasswordField("Password");

		loginButton = new Button("Log in");
		registerButton = new Button("Register");

		
		HorizontalLayout hl = new HorizontalLayout(loginButton,registerButton);
		
		// Add components to the layout
		layout.add(username, password,hl);
		
		add(layout);
		
		
		   // Bind fields to the binder
        binder.forField(username)
            .asRequired("Username is required")
            .bind(User::getUsername, User::setUsername);

        binder.forField(password)
            .asRequired("Password is required")
            .bind(User::getPassword, User::setPassword);


        registerButton.addClickListener(event -> {
            UI.getCurrent().navigate("register");
        });
	}


	public void showNotification(String message) {
		Notification.show(message);
	}

	public void setPresenter(RegistrationPresenter registrationPresenter) {
		this.presenter = registrationPresenter;
	}
}
