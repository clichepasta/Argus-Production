package com.example.security.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "OrderedProduct")
@ToString
public class OrderedProduct {
  @EmbeddedId
  private OrderedProductId id;
  @Column(name = "quantity")
  private int quantity;
  @Column(name = "amount")
  private double amount;
  @Column(name = "profit_amount")
  private double profitAmount;
  @Column(name = "time_required")
  private double timeRequired;
  @Column(name = "status")
  private int status;

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }


  public OrderedProductId getId() {
    return id;
  }

  public void setId(OrderedProductId id) {
    this.id = id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double price) {
    this.amount = price * getQuantity();
  }

  public double getProfitAmount() {
    return profitAmount;
  }

  public void setProfitamount(double profit) {
    this.profitAmount = profit * getQuantity();
  }

  public double getTimeRequired() {
    return timeRequired;
  }

  public void setTimeRequired(double capacity) {
    this.timeRequired = getQuantity() / capacity;
  }
}

