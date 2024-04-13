package com.nithin.vaadinregisterloginnithin.events;

import com.nithin.vaadinregisterloginnithin.entity.User;

public class RegistrationEvent {
	  // Enum for different types of registration events
    public enum Type {
        SUBMIT,
        RESET,
        FORGOT_PASSWORD
    }

    private final Type type;
    private final User user;

    public RegistrationEvent(Type type, User user) {
        this.type = type;
        this.user = user;
    }

    // Getters
    public Type getType() {
        return type;
    }

    public User getUser() {
        return user;
    }
}
