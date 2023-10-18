package com.example.security.services;


import com.example.security.entities.OrderDetails;
import com.example.security.entities.OrderedProduct;
import com.example.security.entities.Product;
import com.example.security.repositories.OrderDetailsRepo;
import com.example.security.repositories.OrderedProductRepo;
import com.example.security.repositories.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

  @Autowired
  private final ProductRepo productRepo;
  @Autowired
  private final OrderDetailsRepo orderDetailsRepo;
  @Autowired
  private final OrderedProductRepo orderedProductRepo;

  @Autowired
  public OrderService(ProductRepo productRepo, OrderDetailsRepo orderDetailsRepo, OrderedProductRepo orderedProductRepo) {
    this.productRepo = productRepo;
    this.orderDetailsRepo = orderDetailsRepo;
    this.orderedProductRepo = orderedProductRepo;
  }


  public List<OrderDetails> getUpdatedList() {

    List<OrderDetails> sortedOrderDetails = orderDetailsRepo.findSortedByProfitPoint();
    List<OrderDetails> updatedOrderDetailsList = new ArrayList<>();

    for (OrderDetails orderDetail : sortedOrderDetails) {

      orderDetail.setStatus(1);

      updatedOrderDetailsList.add(orderDetail);
    }

    System.out.println(updatedOrderDetailsList);


    return updatedOrderDetailsList;
  }

  @Transactional
  public void updateOrderStatus(OrderDetails orderDetails) {
    try {
      OrderDetails existingOrder = orderDetailsRepo.findById(orderDetails.getOrderId()).orElse(null);

      if (existingOrder != null) {
        existingOrder.setStatus(1);

        orderDetailsRepo.save(existingOrder);
      } else {
      }
    } catch (Exception e) {
    }

  }

  @Transactional
  public void updateStatus(int status, int id) {
    OrderDetails order = orderDetailsRepo.getById(id);
    order.setStatus(status);

  }

  public OrderDetails addOrder(OrderDetails orderDetails) {
    int orderID = orderDetails.getOrderId();
    double amount = orderedProductRepo.totalAmount(orderID);
    double profitAmount = orderedProductRepo.totalProfitAmount(orderID);
    double capacity = orderedProductRepo.totalTimeRequired(orderID);
    double constraint = orderedProductRepo.totalConstraintTime(orderID);

    orderDetails.setAmount(amount);
    double profitPoint = profitAmount / capacity;

    orderDetails.setProfitPoint(profitPoint);
    orderDetails.setConstraintTime(constraint);
    orderDetails.setProfitAmount(profitAmount);
    orderDetails.setTimeRequired(capacity);
    orderDetails.setDateAndTime(LocalDateTime.now());
    return orderDetailsRepo.save(orderDetails);
  }

  public OrderedProduct addOrderedProduct(OrderedProduct orderedProduct) {
    int productID = orderedProduct.getId().getProductId();
    Product product = productRepo.getOne(productID);
    double price = product.getSellingPrice();
    orderedProduct.setAmount(price);
    double capacity = product.getCapacityPerDay();
    orderedProduct.setTimeRequired(capacity);
    double profit = product.getProfit();
    orderedProduct.setProfitamount(profit);
    return orderedProductRepo.save(orderedProduct);
  }

  public List<OrderDetails> findAllOrder() {
    return orderDetailsRepo.findAll();
  }

  public List<OrderedProduct> findAllOrderedProduct() {
    return orderedProductRepo.findAll();
  }

}
