package com.vaishuinfo.dto;


import java.io.Serializable;

public class LoginDTO implements Serializable {
	private static final long serialVersionUID = 1L;
    private String userId;
    private String password;
    private String userName;
    private boolean isLoggedIn;
    private boolean Admin;
    private boolean readOnlyUser;

    public LoginDTO() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public boolean isAdmin() {
		return Admin;
	}

	public void setAdmin(boolean admin) {
		Admin = admin;
	}

	public boolean isReadOnlyUser() {
		return readOnlyUser;
	}

	public void setReadOnlyUser(boolean readOnlyUser) {
		this.readOnlyUser = readOnlyUser;
	}


}
