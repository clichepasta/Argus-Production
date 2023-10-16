package com.example.security.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "rejected_orders")
public class RejectedOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reject_id;

    private int order_id;
    private int customer_id;
    private double amount;

    private int deadline;
//    private int status=0;
    private double profit_point;

    public RejectedOrders() {

    }

    public int getReject_id() {
        return reject_id;
    }

    public void setReject_id(int reject_id) {
        this.reject_id = reject_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public double getProfit_point() {
        return profit_point;
    }

    public void setProfit_point(double profit_point) {
        this.profit_point = profit_point;
    }

    public double getExtra_days() {
        return extra_days;
    }

    public void setExtra_days(double extra_days) {
        this.extra_days = extra_days;
    }

    private double extra_days;

    public RejectedOrders(int reject_id, int order_id, int customer_id, double amount, int deadline, double profit_point, double extra_days) {
        this.reject_id = reject_id;
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.amount = amount;
        this.deadline = deadline;
        this.profit_point = profit_point;
        this.extra_days = extra_days;
    }
}
