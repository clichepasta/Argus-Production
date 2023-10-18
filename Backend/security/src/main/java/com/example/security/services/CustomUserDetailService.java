package com.example.security.services;

import com.example.security.entities.Customer;
import com.example.security.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
  @Autowired
  private CustomerRepo customerRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Customer customer = customerRepo.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found!!"));
    return customer;
  }
}
