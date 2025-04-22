package com.example.demo.Main_service;

import com.example.demo.Main_Repository.UserRepository;
import com.example.demo.Main_model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean register(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) return false;
        String hashed = encoder.encode(password);
        userRepository.save(new User(username, hashed));
        return true;
    }

    public boolean authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> encoder.matches(password, user.getPassword()))
                .orElse(false);
    }
}

