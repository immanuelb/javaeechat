package com.javaee.proyek.Controllers;

import com.javaee.proyek.FormBeans.LoginForm;
import com.javaee.proyek.FormBeans.RegisterForm;
import com.javaee.proyek.Models.Users;
import com.javaee.proyek.Services.UsersService;
import com.javaee.proyek.Validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController implements WebMvcConfigurer {
    private UsersService userService;

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    public LogoutController(UsersService userService, LoginValidator loginValidator) {
        this.loginValidator = loginValidator;
        this.userService = userService;
    }

    @RequestMapping(value="/LogOut", method= RequestMethod.GET)
    public ModelAndView LogOut(ModelAndView modelAndView, LoginForm loginForm, HttpSession session) {
        modelAndView.addObject("loginForm", loginForm);
        userService.updateStatusLogin(session.getAttribute("userEmail").toString());
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
