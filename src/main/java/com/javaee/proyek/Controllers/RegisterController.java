package com.javaee.proyek.Controllers;

import com.javaee.proyek.FormBeans.RegisterForm;
import com.javaee.proyek.Models.Users;
import com.javaee.proyek.Services.UsersService;
import com.javaee.proyek.Validator.FieldMatch;
import com.javaee.proyek.Validator.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
public class RegisterController implements WebMvcConfigurer {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersService userService;

    @Autowired
    private RegisterValidator registerValidator;

    @Autowired
    public RegisterController(PasswordEncoder passwordEncoder,UsersService userService, RegisterValidator registerValidator) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.registerValidator = registerValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        // Form target
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);

        if (target.getClass() == RegisterForm.class) {
            dataBinder.setValidator(registerValidator);
        }
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
            System.out.println("user already exist");
            modelAndView.addObject("Message", "Oops!  There is already a user registered with the email provided.");
            modelAndView.setViewName("register");
            bindingResult.reject("email");
        }

        if (bindingResult.hasErrors()) {
            System.out.println("binding error");
            modelAndView.setViewName("register");
        }
        else{
            System.out.println("success insert");
            LocalDateTime now = LocalDateTime.now();

            //not verified by admin
            users.setVerified(0);
            //online
            users.setStatus(1);
            users.setLastLogin(now);
            users.setPassword(passwordEncoder.encode(users.getPassword()));
            userService.saveUser(users);
            modelAndView.addObject("Message", "Your registration has been successful.");
            modelAndView.setViewName("register");
        }
        return modelAndView;
    }
}
