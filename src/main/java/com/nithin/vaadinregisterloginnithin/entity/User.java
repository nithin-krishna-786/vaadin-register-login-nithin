package com.nithin.vaadinregisterloginnithin.entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
public class User {
	
	private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String examPreference;
    private Set<String> locationPreferences;
    private String paymentMethod;

}
