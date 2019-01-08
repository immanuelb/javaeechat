package com.javaee.proyek.Models;

import com.javaee.proyek.Validator.FieldMatch;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import javax.persistence.metamodel.StaticMetamodel;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
    private String email;

    @NotEmpty(message = "Please provide an password")
    private String password;

    @Transient
    @NotEmpty(message = "Please provide an confirm password")
    private String connpassword;

    @NotEmpty(message = "Please provide your first name")
    private String first_name;

    @NotEmpty(message = "Please provide your last name")
    private String last_name;

    private LocalDateTime lastLogin;
    private Integer status;
    private Integer verified;

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
}
