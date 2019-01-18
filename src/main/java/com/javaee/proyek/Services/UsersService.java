package com.javaee.proyek.Services;

import com.javaee.proyek.FormBeans.RegisterForm;
import com.javaee.proyek.Models.Users;
import com.javaee.proyek.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("userService")
public class UsersService {
    private UsersRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Users findByEmailAndPassword(String email, String password){
        return userRepository.findByEmailAndPassword(email,password);
    }

    public Users saveUser(RegisterForm registerForm) {
        LocalDateTime now = LocalDateTime.now();
        String encrytedPassword = this.passwordEncoder.encode(registerForm.getPassword());
        Users user = new Users(registerForm.getEmail(),encrytedPassword,registerForm.getFirst_name(),registerForm.getLast_name(),now,0,0);
        userRepository.save(user);
        return user;
    }

    public Users updateStatusLogin(String email){
        Users userLoggedIn = userRepository.findByEmail(email);
        if(userLoggedIn.getStatus() == 0){
            userLoggedIn.setStatus(1);
        }
        else{
            userLoggedIn.setStatus(0);
        }
        userRepository.save(userLoggedIn);
        return userLoggedIn;
    }
}
