package com.example.security.services;


import com.example.security.entities.Customer;
import com.example.security.entities.OrderDetails;
import com.example.security.entities.OrderedProduct;
import com.example.security.entities.Role;
import com.example.security.repositories.CustomerRepo;
import com.example.security.securities.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class CustomerService {
    @Autowired
    private final CustomerRepo customerRepo;


  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  JwtAuthenticationFilter jwtFilter;


  @Autowired
  private JavaMailSender javaMailSender;

  @Autowired
  public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
        this.javaMailSender = javaMailSender;
    }

    public Customer addCustomer(Customer customer) {

      customer.setCustomerPassword(passwordEncoder.encode(customer.getCustomerPassword()));
        Customer savedCustomer = customerRepo.save(customer);


        sendWelcomeEmail(savedCustomer);

        return savedCustomer;

    }

    public  Customer findById(int customer_id){
      Optional<Customer> result = customerRepo.findById(customer_id);

      Customer theCustomer = null;

      if (result.isPresent()) {
        theCustomer = result.get();
      }
      else {
        // we didn't find the employee
        throw new RuntimeException("Did not find employee id - " + customer_id);
      }

      return theCustomer;
    }


  public void deleteById(int theId) {
    customerRepo.deleteById(theId);
  }


  public List findCustomerById( int customer_id) {
    Customer customer=customerRepo.findByCustomerId(customer_id);
    if(jwtFilter.isSameCustomer(customer)){
      return customerRepo.findCustomerDetailsById(customer_id);
    }
    else {
      return null;
    }

//
//          return customerRepo.findCustomerDetailsById(customer_id);
  }


    private void sendWelcomeEmail(Customer customer) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("rpatnaik@argusoft.com");
        message.setTo(customer.getEmail());
        message.setSubject("Welcome to Our Website");
        message.setText("Dear " + customer.getCustomer_name() + ",\n\nWelcome to our website! Thank you for signing up.");

        javaMailSender.send(message);
        System.out.println("Mail Send");
    }


  public List<Customer> findAllOrder(){
    return customerRepo.findAll();
  }

  public List<Customer> findCustomerByrole(){
    if (jwtFilter.isAdmin()){
      return customerRepo.findCustomerByrole();
    }
    return null;
  }



  public String findRoleByEmailOfCustomer(String email){
    return customerRepo.findRoleByEmail(email);
  }

}
