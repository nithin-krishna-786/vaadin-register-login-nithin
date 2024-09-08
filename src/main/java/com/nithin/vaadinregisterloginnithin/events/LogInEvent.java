package com.nithin.vaadinregisterloginnithin.events;

import com.nithin.vaadinregisterloginnithin.entity.User;

public class LogInEvent {
	  

	public enum Type {
        LOGIN,
        LOGOUT
    }

	private final Type type;
    private final String username;
    private final String password;

    public LogInEvent(Type type, String username, String password) {
		this.type = type;
		this.username = username;
		this.password = password;
	}
    
    public Type getType() {
		return type;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
    
    
    
}
