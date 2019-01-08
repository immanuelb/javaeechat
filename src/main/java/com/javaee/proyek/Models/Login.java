package com.javaee.proyek.Models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class Login {
    @Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
    private String emailLogin;

    @NotEmpty(message = "Please provide an password")
    private String passwordLogin;

    public String getEmailLogin() {
        return emailLogin;
    }

    public void setEmailLogin(String emailLogin) {
        this.emailLogin = emailLogin;
    }

    public String getPasswordLogin() {
        return passwordLogin;
    }

    public void setPasswordLogin(String passwordLogin) {
        this.passwordLogin = passwordLogin;
    }
}
