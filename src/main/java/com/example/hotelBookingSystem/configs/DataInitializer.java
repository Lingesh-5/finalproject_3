package com.example.hotelBookingSystem.configs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hotelBookingSystem.models.Role;
import com.example.hotelBookingSystem.repositories.RoleRepository;


@Configuration
public class DataInitializer {
    
    @Autowired
    private RoleRepository roleRepository;

    @Bean
    public String initData() {
        List<Role> roles = roleRepository.findAll();
        if(roles.isEmpty()) {
            roleRepository.save(new Role(null, "ADMIN"));
            roleRepository.save(new Role(null, "USER"));
        }
        return "Method initiated";
    }
}
