package com.vaishuinfo.dto;


import java.io.Serializable;

public class PasswordDTO implements Serializable {
	private static final long serialVersionUID = 1L;
    private LoginDTO loginBean;
    private String password;

    public PasswordDTO() {
    }

    public void setLoginBean(LoginDTO loginBean) {
        this.loginBean = loginBean;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
