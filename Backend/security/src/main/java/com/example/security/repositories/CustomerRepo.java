package com.example.security.repositories;


import com.example.security.entities.Customer;
import com.example.security.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {



  @Query(value = "SELECT c.* FROM customer c JOIN customer_role cr ON c.customer_id = cr.customer_id WHERE cr.role_id = 'ROLE_CUSTOMER';", nativeQuery = true)
  List<Customer> findCustomerByrole();

  @Query(value = "SELECT c.* FROM customer JOIN customer_role cr ON c.customer_id = cr.customer_id WHERE cr.role_id = 'ROLE_ADMIN';", nativeQuery = true)
  List<Customer> findAdminByrole();

    public Optional<Customer> findByEmail(String email);


  @Query(value = "SELECT cr.role_id FROM customer_role cr JOIN customer c ON cr.customer_id = c.customer_id WHERE c.email = :email", nativeQuery = true)
  String findRoleByEmail(@Param("email") String email);

}
