package com.example.security.controllers;


import com.example.security.entities.Customer;
import com.example.security.entities.OrderDetails;
import com.example.security.repositories.CustomerRepo;
import com.example.security.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    @Autowired
  UserDetailsService userDetailsService;

    @Autowired
    private CustomerRepo customerRepo;


    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        Customer new_customer = customerService.addCustomer(customer);
        return  new ResponseEntity<>(new_customer, HttpStatus.CREATED);
    }

  @GetMapping("/all")
  public ResponseEntity<List<Customer>> getAllCustomer(){

    List<Customer> customers = customerService.findCustomerByrole();
//        List<OrderDetails> demo = orderService.getSortedOrderDetails();
    if(customers==null){
      return new ResponseEntity<>(customers, HttpStatus.UNAUTHORIZED);
    }

    return  new ResponseEntity<>(customers, HttpStatus.OK);
  }

  @GetMapping("/current-user")
  public Customer getLoggedInUser(Principal principal){
    return ((Customer)this.userDetailsService.loadUserByUsername(principal.getName()));

  }



  @GetMapping("/forAdmin")
  public String forAdmin(){
    return customerService.findRoleByEmailOfCustomer("smistry@argusoft.com");
  }

  @GetMapping("/forUser")

  public String forUser(){
    return "This URL is only accessible to the user";
  }




  @DeleteMapping("/delete/{customer_id}")
  public String deleteCustomer(@PathVariable int customer_id){
    Customer theCustomer = customerService.findById(customer_id);

    if (theCustomer == null) {
      throw new RuntimeException("Customer id not found - " + customer_id);
    }
    customerService.deleteById(customer_id);
    return  "Deleted Customer id - " + customer_id;
  }


}
