package com.javaee.proyek.Controllers;

import com.javaee.proyek.Models.Users;
import com.javaee.proyek.Services.UsersService;
import com.javaee.proyek.Validator.FieldMatch;
import com.javaee.proyek.Validator.FieldMatchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;

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
    public ModelAndView showLoginPage(ModelAndView modelAndView,Users users) {
        modelAndView.addObject("loginForm", users);
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
