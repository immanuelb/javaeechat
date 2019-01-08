package com.javaee.proyek.Validator;

import com.javaee.proyek.FormBeans.LoginForm;
import com.javaee.proyek.Models.Users;
import com.javaee.proyek.Services.UsersService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {
    // common-validator library.
    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersService usersService;

    // The classes are supported by this validator.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == LoginForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginForm loginForm = (LoginForm) target;

        // Check the fields of LoginForm.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.appLoginForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.appLoginForm.password");

        if (!this.emailValidator.isValid(loginForm.getEmail())) {
            // Invalid email.
            errors.rejectValue("email", "Pattern.appLoginForm.email");
        } else if (loginForm.getEmail() == null) {
            // Email not found.
            errors.rejectValue("email", "NotFound.appLoginForm.email");

        }

        if (!errors.hasErrors()) {
            Users dbUser = usersService.findByEmailAndPassword(loginForm.getEmail(),passwordEncoder.encode(loginForm.getPassword()));
            /*if (!passwordEncoder.encode(loginForm.getPassword()).equals(dbUser.getPassword())) {
                errors.rejectValue("password", "NotFound.appLoginForm.password");
            }*/
            if(dbUser == null){
                errors.rejectValue("password", "NotFound.appLoginForm.password");
            }
        }
    }
}
