package com.example.security.controllers;

import com.example.security.entities.Customer;
import com.example.security.model.JwtRequest;
import com.example.security.model.JwtResponse;

import com.example.security.securities.JwtHelper;
import com.example.security.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class  AuthController {

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;


    @Autowired
    private CustomerService customerService;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

      this.doAuthenticate(request.getEmail(), request.getPassword());


      UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());


      String token = this.helper.generateToken(userDetails,customerService.findRoleByEmailOfCustomer(request.getEmail()));

      JwtResponse response = JwtResponse.builder()
        .jwtToken(token)
        .username(userDetails.getUsername()).build();
      System.out.println(token);
      return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }


    @PostMapping("/create-user")
    public Customer createCustomer(@RequestBody Customer customer){

      return customerService.addCustomer(customer);
    }
}
