package com.javaee.proyek.Validator;

import com.javaee.proyek.Models.Users;
import org.apache.commons.validator.routines.EmailValidator;
import com.javaee.proyek.FormBeans.RegisterForm;
import com.javaee.proyek.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class RegisterValidator implements Validator {
    // common-validator library.
    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Autowired
    private UsersService usersService;

    // The classes are supported by this validator.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == RegisterForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterForm registerForm = (RegisterForm) target;

        // Check the fields of AppUserForm.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "first_name", "NotEmpty.appRegisterForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "last_name", "NotEmpty.appRegisterForm.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.appRegisterForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.appRegisterForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "connpassword", "NotEmpty.appRegisterForm.confirmPassword");

        if (!this.emailValidator.isValid(registerForm.getEmail())) {
            // Invalid email.
            errors.rejectValue("email", "Pattern.appRegisterForm.email");
        } else if (registerForm.getId() == null) {
            Users dbUser = usersService.findByEmail(registerForm.getEmail());
            if (dbUser != null) {
                // Email has been used by another account.
                errors.rejectValue("email", "Duplicate.appRegisterForm.email");
            }
        }

        if (!errors.hasErrors()) {
            if (!registerForm.getConnpassword().equals(registerForm.getPassword())) {
                errors.rejectValue("confirmPassword", "Match.appRegisterForm.confirmPassword");
            }
        }
    }
}
