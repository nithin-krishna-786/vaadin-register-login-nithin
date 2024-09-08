package com.nithin.vaadinregisterloginnithin.views;


import org.springframework.beans.factory.annotation.Autowired;

import com.nithin.vaadinregisterloginnithin.entity.User;
import com.nithin.vaadinregisterloginnithin.presenter.LoginPresenter;
import com.nithin.vaadinregisterloginnithin.presenter.RegistrationPresenter;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route("loggedin")
public class LoggedInView extends VerticalLayout {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 8765440703771392684L;


	@Autowired
	private LoginPresenter presenter;
	
	private Button logout;

	public LoggedInView() {

		VerticalLayout layout = new VerticalLayout();
		//MINIMAL SPACING AND PADDDING
		layout.setPadding(false); // Remove padding around the layout
		layout.setSpacing(false); // Remove spacing between components
		
		
		
		layout.setSizeFull();
		layout.setAlignItems(Alignment.CENTER);
		layout.setJustifyContentMode(JustifyContentMode.CENTER);
		
		H1 bigLabel = new H1("USER LOGGED IN");
		add(bigLabel);
		
		logout = new Button("Log out"); 
		add(logout);
		
		logout.addClickListener(event -> {
	            UI.getCurrent().navigate("login");
	        });
		
	}

	public void showNotification(String message) {
		Notification.show(message);
	}

	public void setPresenter(LoginPresenter loginPresenter) {
		this.presenter = loginPresenter;
	}
}
