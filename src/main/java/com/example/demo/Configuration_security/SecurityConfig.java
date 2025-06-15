package com.example.demo.Configuration_security;


import org.springframework.security.core.userdetails.User;
import com.example.demo.Tokens.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import jakarta.servlet.http.HttpServletRequest;


import java.util.Collections;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/whatsapp-send/sendtext").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login();

        http.addFilterBefore((req, res, chain) -> {
            HttpServletRequest request = (HttpServletRequest) req;
            String path = request.getRequestURI();

            // ✅ Skip JWT filter for permitted endpoints
            if (path.startsWith("/whatsapp-send/")) {
                chain.doFilter(req, res);
                return;
            }

            String header = request.getHeader("Authorization");
            if (header != null && header.startsWith("Bearer ")) {
                String token = header.substring(7);
                if (jwtUtil.validateToken(token)) {
                    var auth = new UsernamePasswordAuthenticationToken(
                            new User(jwtUtil.getUsernameFromToken(token), "", Collections.emptyList()),
                            null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }

            chain.doFilter(req, res);
        }, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
