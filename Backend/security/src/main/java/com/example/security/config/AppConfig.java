package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.SecurityMarker;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
@Configuration
public class AppConfig {
//    @SecurityMarker
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userDetails = User.builder().username("Rahul")
//                .password(passwordEncoder().encode("Rahul")).roles("ADMIN").
//                build();
//        UserDetails userDetails2 = User.builder().username("Satya")
//                .password(passwordEncoder().encode("Satya")).roles("ADMIN").
//                build();
//        return new InMemoryUserDetailsManager(userDetails,userDetails2);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
