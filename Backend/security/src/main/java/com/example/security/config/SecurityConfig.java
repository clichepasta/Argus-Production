package com.example.security.config;

import com.example.security.securities.JwtAuthenticationEntryPoint;
import com.example.security.securities.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class SecurityConfig {
  @Autowired
  private JwtAuthenticationEntryPoint point;
  @Autowired
  private JwtAuthenticationFilter filter;
  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private PasswordEncoder passwordEncoder;


  @Primary
  public UserDetailsManager userDetailsManager(DataSource dataSource) {
    JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);

    theUserDetailsManager.setUsersByUsernameQuery("select * from customer where email=?");

    theUserDetailsManager.setAuthoritiesByUsernameQuery("select customer_id,role_id from roles where customer_id=?");


    return theUserDetailsManager;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


    http.csrf(csrf -> csrf.disable()).cors().and().authorizeRequests().requestMatchers("/auth/login").permitAll().requestMatchers("/auth/create-user").permitAll()

      .anyRequest().authenticated().and().exceptionHandling(ex -> ex.authenticationEntryPoint(point)).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


    http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

    http.httpBasic(Customizer.withDefaults());
    return http.build();


  }


  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder);
    return provider;

  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
    return builder.getAuthenticationManager();
  }

}
