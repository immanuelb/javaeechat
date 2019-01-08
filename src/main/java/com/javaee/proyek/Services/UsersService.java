package com.javaee.proyek.Services;

import com.javaee.proyek.Models.Users;
import com.javaee.proyek.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UsersService {
    private UsersRepository userRepository;

    @Autowired
    public UsersService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Users findByEmailAndPassword(String email, String password){
        Users user_exist = userRepository.findByEmailAndPassword(email,password);
        if(user_exist != null){
            if(user_exist.getVerified() == 1){
                user_exist = userRepository.findByEmailAndPassword(email,password);
            }
            else{
                user_exist = null;
            }
        }
        return user_exist;
    }

    public void saveUser(Users user) {
        userRepository.save(user);
    }
}
