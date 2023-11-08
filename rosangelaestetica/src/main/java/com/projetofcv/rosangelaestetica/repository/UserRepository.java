package com.projetofcv.rosangelaestetica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projetofcv.rosangelaestetica.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{ 
    
    @Query("select j from User j where j.name = :name and j.password = :password")
    public User buscarLogin(String name, String password);
}
