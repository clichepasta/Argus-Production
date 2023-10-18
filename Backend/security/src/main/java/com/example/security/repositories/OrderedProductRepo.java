package com.example.security.repositories;

import com.example.security.entities.OrderedProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedProductRepo extends JpaRepository<OrderedProduct, Integer> {

  @Query(value = "SELECT SUM(amount) FROM ordered_product WHERE order_id = :orderID", nativeQuery = true)
  double totalAmount(@Param("orderID") int orderID);

  @Query(value = "SELECT SUM(profit_amount) FROM ordered_product WHERE order_id = :orderID", nativeQuery = true)
  double totalProfitAmount(@Param("orderID") int orderID);

  @Query(value = "SELECT SUM(p.chain_change_time) AS total_chain_change_time FROM ordered_product AS op JOIN product AS p ON op.product_id = p.product_id WHERE op.order_id = 101;", nativeQuery = true)
  double totalConstraintTime(@Param("order_iD") int order_iD);

  @Query(value = "select * from ordered_product AS o1 order by (select deadline from order_details AS o2 where o1.order_id=o2.order_id) ", nativeQuery = true)
  List<OrderedProduct> getOrderedProductSortedByDeadline();

  @Query(value = "select * from ordered_product WHERE product_id = :product_id And order_id = :order_id", nativeQuery = true)
  OrderedProduct getOrderedProductByProductId(@Param("product_id") int product_id, @Param("order_id") int order_id);

  @Query(value = "SELECT SUM(time_required) FROM ordered_product WHERE order_id = :orderID", nativeQuery = true)
  double totalTimeRequired(@Param("orderID") int orderID);
}
