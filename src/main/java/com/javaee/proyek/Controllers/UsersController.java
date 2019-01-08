package com.javaee.proyek.Controllers;

import com.javaee.proyek.FormBeans.LoginForm;
import com.javaee.proyek.FormBeans.RegisterForm;
import com.javaee.proyek.Services.UsersService;
import com.javaee.proyek.Validator.LoginValidator;
import com.javaee.proyek.Validator.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class UsersController implements WebMvcConfigurer {
    @Autowired
    private UsersService userService;

    /*@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/result").setViewName("result");
    }*/

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    public UsersController(UsersService userService, LoginValidator loginValidator) {
        this.userService = userService;
        this.loginValidator = loginValidator;
    }

    @GetMapping("/")
    public ModelAndView showLoginPage(ModelAndView modelAndView, LoginForm loginForm) {
        modelAndView.addObject("loginForm", loginForm);
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
