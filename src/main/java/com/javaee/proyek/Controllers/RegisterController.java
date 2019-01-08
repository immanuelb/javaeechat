package com.javaee.proyek.Controllers;

import com.javaee.proyek.Models.Users;
import com.javaee.proyek.Services.UsersService;
import com.javaee.proyek.Validator.FieldMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@FieldMatch(first = "password", second = "connpassword", message = "Passwords are not equal.")
public class RegisterController implements WebMvcConfigurer {
    private UsersService userService;

    @Autowired
    public RegisterController(UsersService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/register", method= RequestMethod.POST, params="action=Login")
    public ModelAndView viewLogin(ModelAndView modelAndView, Users users) {
        modelAndView.addObject("loginForm", users);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value="/register", method=RequestMethod.POST, params="action=Register")
    public ModelAndView checkRegister(ModelAndView modelAndView, @ModelAttribute("registerForm") @Valid Users users, BindingResult bindingResult, HttpServletRequest request) {
        // Lookup user in database by e-mail
        Users userExists = userService.findByEmail(users.getEmail());
        System.out.println(userExists);

        if (userExists != null) {
            modelAndView.addObject("Message", "Oops!  There is already a user registered with the email provided.");
            modelAndView.setViewName("register");
            bindingResult.reject("email");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("register");
        }
        else{
            LocalDateTime now = LocalDateTime.now();

            //not verified by admin
            users.setVerified(0);
            //online
            users.setStatus(1);
            users.setLastLogin(now);
            //users.setPassword(bCryptPasswordEncoder.encode(users.getPassword().trim().toString()));
            userService.saveUser(users);
            modelAndView.addObject("Message", "Your registration has been successful.");
            modelAndView.setViewName("register");
        }
        return modelAndView;
    }
}
