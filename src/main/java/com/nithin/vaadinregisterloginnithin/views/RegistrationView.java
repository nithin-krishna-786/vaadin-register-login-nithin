package com.nithin.vaadinregisterloginnithin.views;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nithin.vaadinregisterloginnithin.entity.User;
import com.nithin.vaadinregisterloginnithin.events.RegistrationEvent;
import com.nithin.vaadinregisterloginnithin.presenter.RegistrationPresenter;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.RegexpValidator;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route("")
@RouteAlias("register")
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
	private Button clearButton;

	private Binder<User> binder = new Binder<>(User.class);

	@Autowired
	private RegistrationPresenter presenter;

	public RegistrationView() {

		VerticalLayout layout = new VerticalLayout();
		//MINIMAL SPACING AND PADDDING
		layout.setPadding(false); // Remove padding around the layout
		layout.setSpacing(false); // Remove spacing between components
		
		layout.setSizeFull();
		layout.setAlignItems(Alignment.CENTER);
		layout.setJustifyContentMode(JustifyContentMode.CENTER);

		// Create form fields
		username = new TextField("Username");
		username.setRequiredIndicatorVisible(true);
		
		password = new PasswordField("Password");
		email = new EmailField("Email");
		email.setRequired(true);

		phoneNumber = new TextField("Phone Number");
		phoneNumber.setPattern("\\d*"); // Allow digits only
		phoneNumber.setErrorMessage("Invalid phone number");
		phoneNumber.setMaxLength(12); 
		
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
		clearButton = new Button("Clear");
		
		HorizontalLayout hl = new HorizontalLayout(registerButton,clearButton);
		
		// Add components to the layout
		layout.add(username, password, email, phoneNumber, dateOfBirth, genderRadioGroup, examPreference, locationPreferences,
				paymentMethod,hl);
		
		add(layout);
		

//OLD
//		registerButton.addClickListener(e -> {
//			User user = new User();
//			user.setUsername(username.getValue());
//			user.setPassword(password.getValue());
//			user.setEmail(email.getValue());
//			user.setPhoneNumber(phoneNumber.getValue());
//			user.setDateOfBirth(dateOfBirth.getValue());
//			user.setGender(genderRadioGroup.getValue());
//			user.setExamPreference(examPreference.getValue());
//			user.setLocationPreferences(locationPreferences.getValue());
//			user.setPaymentMethod(paymentMethod.getValue());
//			this.registerButtonClicked(user);
//		});
//OLD		
		
		   // Bind fields to the binder
        binder.forField(username)
            .asRequired("Username is required")
            .bind(User::getUsername, User::setUsername);

        binder.forField(password)
            .asRequired("Password is required")
            .bind(User::getPassword, User::setPassword);

        binder.forField(email)
            .asRequired("Email is required")
            .withValidator(new EmailValidator("This doesn't look like a valid email address"))
            .bind(User::getEmail, User::setEmail);
        
     // Bind the phoneNumber field
        binder.forField(phoneNumber)
            .asRequired("Phone number is required")
            .withValidator(new RegexpValidator("Invalid phone number", "\\d*"))
            .bind(User::getPhoneNumber, User::setPhoneNumber);

        // Bind the dateOfBirth field
        binder.forField(dateOfBirth)
            .asRequired("Date of birth is required")
            .bind(User::getDateOfBirth, User::setDateOfBirth);

        // Bind the genderRadioGroup field
        binder.forField(genderRadioGroup)
            .asRequired("Gender is required")
            .bind(User::getGender, User::setGender);

        // Bind the examPreference field
        binder.forField(examPreference)
            .asRequired("Exam preference is required")
            .bind(User::getExamPreference, User::setExamPreference);

        // Bind the locationPreferences field
        binder.forField(locationPreferences)
            .asRequired("Location preference is required")
            .bind(User::getLocationPreferences, User::setLocationPreferences);

        // Bind the paymentMethod field
        binder.forField(paymentMethod)
            .asRequired("Payment method is required")
            .bind(User::getPaymentMethod, User::setPaymentMethod);
        
        clearButton.addClickListener( e ->
        {
        	username.clear();
        	password.clear();
        	email.clear();
        	phoneNumber.clear();
        	dateOfBirth.clear();
        	genderRadioGroup.clear();
        	examPreference.clear();
        	locationPreferences.clear();
        	paymentMethod.clear();
        });

        // Handle form submission
        registerButton.addClickListener(event -> {
            if (binder.validate().isOk()) {
                // Proceed with registration
                User user = new User();
                binder.writeBeanIfValid(user);
                registerButtonClicked(user);
                binder.readBean(null); // Clear the form
            } else {
                // Show validation errors
                BinderValidationStatus<User> status = binder.validate();
                String errorMessages = status.getValidationErrors().stream()
                                             .map(ValidationResult::getErrorMessage)
                                             .collect(Collectors.joining(","));
                Notification.show("Please correct the following errors:\n" + errorMessages, 5000, Position.BOTTOM_START);
            }
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
