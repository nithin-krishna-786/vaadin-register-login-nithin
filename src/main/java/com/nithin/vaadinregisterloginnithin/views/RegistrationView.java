package com.nithin.vaadinregisterloginnithin.views;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nithin.vaadinregisterloginnithin.entity.User;
import com.nithin.vaadinregisterloginnithin.events.RegistrationEvent;
import com.nithin.vaadinregisterloginnithin.presenter.RegistrationPresenter;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.router.Route;

@Route("register")
public class RegistrationView extends VerticalLayout {

	private TextField username;
	private PasswordField password;
	private EmailField email;
	private TextField phoneNumber;
	private DatePicker dateOfBirth;
	private RadioButtonGroup<String> genderRadioGroup;
	private ComboBox<String> examPreference;
	private CheckboxGroup<String> locationPreferences;
	private RadioButtonGroup<String> paymentMethod;
	private Button registerButton;

	@Autowired
	private RegistrationPresenter presenter;

	public RegistrationView() {

		// Create form fields
		username = new TextField("Username");
		password = new PasswordField("Password");
		email = new EmailField("Email");

		phoneNumber = new TextField("Phone Number");
		phoneNumber.setPattern("\\d*"); // Allow digits only
		phoneNumber.setErrorMessage("Invalid phone number");

		dateOfBirth = new DatePicker("Date of Birth");
		dateOfBirth.setClearButtonVisible(true);
		dateOfBirth.setPlaceholder("Select a date");

		genderRadioGroup = new RadioButtonGroup<>();
		genderRadioGroup.setLabel("Gender");
		genderRadioGroup.setItems("Male", "Female", "Other");
		genderRadioGroup.setValue("Male");

		examPreference = new ComboBox<>("Exam Preference");
		examPreference.setItems("Easy", "Medium", "Hard");
		examPreference.setPlaceholder("Select one");
		examPreference.setAllowCustomValue(false);
		examPreference.setRequiredIndicatorVisible(true);

		locationPreferences = new CheckboxGroup<>();
		locationPreferences.setLabel("Location Preferences");
		locationPreferences.setItems("London", "New York", "Paris");

		paymentMethod = new RadioButtonGroup<>();
		paymentMethod.setLabel("Payment Method");
		paymentMethod.setItems("Cash", "Credit Card", "UPI");

		registerButton = new Button("Register");
		// Add components to the layout
		add(username, password, email, phoneNumber, dateOfBirth, genderRadioGroup, examPreference, locationPreferences,
				paymentMethod, registerButton);

		registerButton.addClickListener(e -> {
			User user = new User();
			user.setUsername(username.getValue());
			user.setPassword(password.getValue());
			user.setEmail(email.getValue());
			user.setPhoneNumber(phoneNumber.getValue());
			user.setDateOfBirth(dateOfBirth.getValue());
			user.setGender(genderRadioGroup.getValue());
			user.setExamPreference(examPreference.getValue());
			user.setLocationPreferences(locationPreferences.getValue());
			user.setPaymentMethod(paymentMethod.getValue());
			this.registerButtonClicked(user);
		});

	}

	private void registerButtonClicked(User user) {
		presenter.onEvent(new RegistrationEvent(RegistrationEvent.Type.SUBMIT, user));
	}

	public void showNotification(String message) {
		Notification.show(message);
	}

	public void setPresenter(RegistrationPresenter registrationPresenter) {
			this.presenter = registrationPresenter;
	}
}
