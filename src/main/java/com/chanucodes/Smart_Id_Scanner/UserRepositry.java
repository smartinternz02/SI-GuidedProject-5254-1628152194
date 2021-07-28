package com.chanucodes.Smart_Id_Scanner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositry extends JpaRepository<User,String> {

	User findByEmail(String email);
}