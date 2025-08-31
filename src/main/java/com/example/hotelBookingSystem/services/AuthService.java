package com.example.hotelBookingSystem.services;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hotelBookingSystem.models.Role;
import com.example.hotelBookingSystem.models.User;
import com.example.hotelBookingSystem.pojos.LoginPayload;
import com.example.hotelBookingSystem.pojos.UserPayload;
import com.example.hotelBookingSystem.repositories.RoleRepository;
import com.example.hotelBookingSystem.repositories.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthService {

    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    public void saveRegister(UserPayload payload) {
        User user = new User();
        user.setName(payload.getName());
        user.setEmail(payload.getEmail());
        user.setPassword(passwordEncoder.encode(payload.getPassword()));
        Set<Role> role = roleRepository.findByName(payload.getRole());
        user.setRoles(role);
        userRepository.save(user);
    }

    public ResponseEntity<?> verify(LoginPayload payload) {
        User user = userRepository.findByEmail(payload.getEmail());
        if(user==null) {
            return ResponseEntity.ok(Map.of("message", "Failed, User does not exist"));
        }else if(!passwordEncoder.matches(payload.getPassword(), user.getPassword())) {
            return ResponseEntity.ok(Map.of("message", "Password is not matched"));
        }
        List<String> roles = user.getRoles().stream().map(Role::getName).toList();

        byte[] keyBytes = Base64.getDecoder().decode(jwtSecret);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        String token = Jwts.builder()
            .subject(user.getEmail())
            .claim("roles", roles)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
            .signWith(key,  Jwts.SIG.HS256)
            .compact();
        return ResponseEntity.ok(Map.of("token", token, "user", user, "message", "Login success"));
    }

}