package com.javaee.proyek.FormBeans;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class RegisterForm {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String email;
    private String password;
    private String connpassword;
    private String first_name;
    private String last_name;
    private LocalDateTime lastLogin;
    private Integer status;
    private Integer verified;

    public RegisterForm() {
    }

    public RegisterForm(String email, String password, String connpassword, String first_name, String last_name, LocalDateTime lastLogin, Integer status, Integer verified) {
        this.email = email;
        this.password = password;
        this.connpassword = connpassword;
        this.first_name = first_name;
        this.last_name = last_name;
        this.lastLogin = lastLogin;
        this.status = status;
        this.verified = verified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConnpassword() {
        return connpassword;
    }

    public void setConnpassword(String connpassword) {
        this.connpassword = connpassword;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }
}
