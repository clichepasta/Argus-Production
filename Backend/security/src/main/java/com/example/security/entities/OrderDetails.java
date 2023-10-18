package com.example.security.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "OrderDetails")
@ToString
public class OrderDetails {


  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "order_id")
  private int orderId;

  @Column(name = "customer_id")
  private int customerId;

  @Column(name = "amount")
  private double amount;

  @Column(name = "profit_amount")
  private double profitAmount;

  @Column(name = "deadline")
  private int deadline;

  @Column(name = "status")
  private int status = 0;
  @Column(name = "time_required")
  private double timeRequired;
  @Column(name = "date_and_time")
  private LocalDateTime dateAndTime;
  @Column(name = "profit_point")
  private double profit_point;
  @Column(name = "constraint_time")
  private double constraintTime;
  public double getConstraintTime() {
    return constraintTime;
  }

  public void setConstraintTime(double constraintTime) {
    this.constraintTime = constraintTime;
  }



  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }


  public double getProfitPoint() {
    return profit_point;
  }

  public void setProfitPoint(double profit_point) {
    this.profit_point = profit_point;
  }


  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public double getProfitAmount() {
    return profitAmount;
  }

  public void setProfitAmount(double profitAmount) {
    this.profitAmount = profitAmount;
  }

  public int getDeadline() {
    return deadline;
  }

  public void setDeadline(int deadline) {
    this.deadline = deadline;
  }

  public double getTimeRequired() {
    return timeRequired;
  }

  public void setTimeRequired(double timeRequired) {
    this.timeRequired = timeRequired;
  }

  public LocalDateTime getDateAndTime() {
    return dateAndTime;
  }

  public void setDateAndTime(LocalDateTime dateAndTime) {
    this.dateAndTime = dateAndTime;
  }
}
