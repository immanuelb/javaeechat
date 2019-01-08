package com.javaee.proyek.Controllers;

import com.javaee.proyek.FormBeans.LoginForm;
import com.javaee.proyek.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class UsersController implements WebMvcConfigurer {
    private UsersService userService;

    @Autowired
    public UsersController(UsersService userService) {
        this.userService = userService;
    }

    /*@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/result").setViewName("result");
    }*/

    @GetMapping("/")
    public ModelAndView showLoginPage(ModelAndView modelAndView, LoginForm loginForm) {
        modelAndView.addObject("loginForm", loginForm);
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
