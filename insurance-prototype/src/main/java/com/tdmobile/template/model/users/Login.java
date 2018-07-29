package com.tdmobile.template.model.users;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class Login implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1136893126866477017L;

    private String user;
    private String password;

    public String getUser() {
	return user;
    }

    public void setUser(String user) {
	this.user = user;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

}
