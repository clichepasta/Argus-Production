package com.example.security.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "rejected_orders")
public class RejectedOrders {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "reject_id")
  private int rejectId;
  @Column(name = "order_id")
  private int orderId;
  @Column(name = "customer_id")
  private int customerId;
  @Column(name = "amount")
  private double amount;
  @Column(name = "deadline")
  private int deadline;
  @Column(name = "profit_point")
  private double profitPoint;
  @Column(name = "extra_days")
  private double extraDays;


}
