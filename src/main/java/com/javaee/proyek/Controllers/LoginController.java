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

import javax.validation.Valid;

@Controller
public class LoginController implements WebMvcConfigurer {
    private UsersService userService;

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    public LoginController(UsersService userService, LoginValidator loginValidator) {
        this.loginValidator = loginValidator;
        this.userService = userService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        // Form target
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);

        if (target.getClass() == LoginForm.class) {
            dataBinder.setValidator(loginValidator);
        }
    }

    @RequestMapping(value="/check", method= RequestMethod.POST, params="action=Login")
    public ModelAndView checkLogin(ModelAndView modelAndView, @ModelAttribute("loginForm")@Validated LoginForm loginForm, BindingResult bindingResult) {
        // Lookup user in database by e-mail
        /*Users userExists = userService.findByEmailAndPassword(loginForm.getEmail(),loginForm.getPassword());
        System.out.println(userExists);

        if (userExists == null) {
            System.out.println("Error");
            modelAndView.addObject("NotFound", "Oops! Can not find the user. Please try again");
            modelAndView.addObject("loginForm", loginForm);
            modelAndView.setViewName("index");
        }*/

        if (bindingResult.hasErrors()) {
            System.out.println("Binding Error");
            modelAndView.addObject("loginForm", loginForm);
            modelAndView.setViewName("index");
        }
        else{
            System.out.println("Success Login");
            modelAndView.setViewName("result");
        }
        return modelAndView;
    }

    @RequestMapping(value="/check", method=RequestMethod.POST, params="action=Register")
    public ModelAndView viewRegister(ModelAndView modelAndView, RegisterForm registerForm) {
        modelAndView.addObject("registerForm", registerForm);
        modelAndView.setViewName("register");
        return modelAndView;
    }
}
