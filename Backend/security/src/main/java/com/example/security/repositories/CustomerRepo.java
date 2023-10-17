package com.example.security.repositories;


import com.example.security.entities.Customer;
import com.example.security.entities.OrderDetails;
import com.example.security.entities.OrderedProduct;
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

    public Customer findByCustomerId(int customer_id);

  @Query(value = "SELECT cr.role_id FROM customer_role cr JOIN customer c ON cr.customer_id = c.customer_id WHERE c.email = :email", nativeQuery = true)
  String findRoleByEmail(@Param("email") String email);

  @Query(value = "SELECT od.order_id, op.product_id, op.quantity, op.amount, p.product_name,p.manufacture_price FROM order_details AS od JOIN ordered_product AS op ON od.order_id = op.order_id JOIN product AS p ON op.product_id = p.product_id WHERE od.customer_id = :customer_id", nativeQuery = true)
  List findCustomerDetailsById(@Param("customer_id") int customer_id);

}
