package com.example.security.repositories;

import com.example.security.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

  @Query(value = "SELECT * FROM product ", nativeQuery = true)
  List<Product> getProductList();

  @Query(value = "SELECT chain_change_time FROM product where product_id = :product_id ", nativeQuery = true)
  double getChainChangeTime(@Param("product_id") int product_id);
}
