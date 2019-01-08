package com.javaee.proyek.Repositories;

import com.javaee.proyek.FormBeans.RegisterForm;
import com.javaee.proyek.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

    Users findByEmailAndPassword(String email, String password);

    Users saveUser(RegisterForm form);
}
