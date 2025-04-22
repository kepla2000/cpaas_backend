package com.example.demo.Main_controller;

import com.example.demo.Main_service.UserService;
import com.example.demo.Tokens.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public Map<String, String> signup(@RequestBody Map<String, String> body) {
        boolean created = userService.register(body.get("username"), body.get("password"));
        return created ? Map.of("message", "User registered successfully")
                : Map.of("error", "User already exists");
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {
        if (!userService.authenticate(body.get("username"), body.get("password")))
            throw new RuntimeException("Invalid credentials");
        return Map.of("token", jwtUtil.generateToken(body.get("username")));
    }
}
