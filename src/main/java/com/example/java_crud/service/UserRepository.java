package com.example.java_crud.service;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.java_crud.models.User;

public interface UserRepository  extends JpaRepository<User, Integer > {

}