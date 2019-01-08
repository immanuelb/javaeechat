package com.javaee.proyek.Controllers;

import com.javaee.proyek.Models.Users;
import com.javaee.proyek.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class LoginController implements WebMvcConfigurer {
    private UsersService userService;

    @Autowired
    public LoginController(UsersService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/check", method= RequestMethod.POST, params="action=Login")
    public ModelAndView checkLogin(ModelAndView modelAndView, @ModelAttribute("loginForm")@Valid Users users, BindingResult bindingResult) {
        // Lookup user in database by e-mail
        Users userExists = userService.findByEmailAndPassword(users.getEmail(),users.getPassword());
        System.out.println(userExists);

        if (userExists == null) {
            System.out.println("Error");
            modelAndView.addObject("NotFound", "Oops! Can not find the user. Please try again");
            modelAndView.addObject("loginForm", users);
            modelAndView.setViewName("index");
            bindingResult.reject("email");
            bindingResult.reject("password");
        }

        if (bindingResult.hasErrors()) {
            System.out.println("Binding Error");
            modelAndView.addObject("loginForm", users);
            modelAndView.setViewName("index");
        }
        else{
            System.out.println("Success Login");
            modelAndView.setViewName("result");
        }
        return modelAndView;
    }

    @RequestMapping(value="/check", method=RequestMethod.POST, params="action=Register")
    public ModelAndView viewRegister(ModelAndView modelAndView,Users users) {
        modelAndView.addObject("registerForm", users);
        modelAndView.setViewName("register");
        return modelAndView;
    }
}
