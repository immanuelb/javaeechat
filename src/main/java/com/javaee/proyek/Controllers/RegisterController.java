package com.javaee.proyek.Controllers;

import com.javaee.proyek.FormBeans.RegisterForm;
import com.javaee.proyek.Models.Users;
import com.javaee.proyek.Services.UsersService;
import com.javaee.proyek.Validator.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public ModelAndView viewLogin(ModelAndView modelAndView, RegisterForm registerForm) {
        modelAndView.addObject("loginForm", registerForm);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value="/register", method=RequestMethod.POST, params="action=Register")
    public ModelAndView checkRegister(ModelAndView modelAndView, @ModelAttribute("registerForm") @Validated RegisterForm registerForm, BindingResult bindingResult) {
        // Lookup user in database by e-mail
        /*Users userExists = userService.findByEmail(registerForm.getEmail());
        System.out.println(userExists);

        if (userExists != null) {
            System.out.println("user already exist");
            modelAndView.addObject("Message", "Oops!  There is already a user registered with the email provided.");
            modelAndView.setViewName("register");
        }*/

        if (bindingResult.hasErrors()) {
            System.out.println("binding error");
            modelAndView.setViewName("register");
        }
        else{
            System.out.println("success insert");
            userService.saveUser(registerForm);
            modelAndView.addObject("Message", "Your registration has been successful.");
            modelAndView.setViewName("register");
        }
        return modelAndView;
    }
}
