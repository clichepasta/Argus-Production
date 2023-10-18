package com.example.security.entities;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@Table(name = "Product")
@ToString
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "product_id")
  private int productId;

  @Column(name = "product_name")
  private String productName;

  @Column(name = "capacity_per_day")
  private int capacityPerDay;

  @Column(name = "manufacture_price")
  private double manufacturePrice;

  @Column(name = "selling_price")
  private double sellingPrice;

  @Column(name = "profit")
  private double profit;

  @Column(name = "chain_change_time")
  private double chainChangeTime;

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public int getCapacityPerDay() {
    return capacityPerDay;
  }

  public void setCapacityPerDay(int capacityPerDay) {
    this.capacityPerDay = capacityPerDay;
  }

  public double getManufacturePrice() {
    return manufacturePrice;
  }

  public void setManufacturePrice(double manufacturePrice) {
    this.manufacturePrice = manufacturePrice;
  }

  public double getSellingPrice() {
    return sellingPrice;
  }

  public void setSellingPrice(double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  public double getProfit() {
     return sellingPrice - manufacturePrice;
  }

  public void setProfit(double profit) {
    this.profit = profit;
  }

  public double getChainChangeTime() {
    return chainChangeTime;
  }

  public void setChainChangeTime(double chainChangeTime) {
    this.chainChangeTime = chainChangeTime;
  }


}
