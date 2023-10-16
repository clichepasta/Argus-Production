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
@Getter
@Setter
@ToString
public class OrderedProduct {
    @EmbeddedId
    private OrderedProductId id;
    private int quantity;
    private double amount;
    private double profitamount;
    @Column(name="time_required")
    private double timeRequired;

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
//getter and setter

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
        this.amount = price*getQuantity();
    }

    public double getProfitamount() {
        return profitamount;
    }

    public void setProfitamount(double profit) {
        this.profitamount = profit*getQuantity();
    }

    public double getTimeRequired() {
        return timeRequired;
    }

    public void setTimeRequired(double capacity) {
        this.timeRequired = getQuantity()/capacity;
    }
}

