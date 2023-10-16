package com.example.security.entities;

public class OrderManufacturing {
    private int orderId;
    private double time_required;
    private double deadline;

    //constructor

    public OrderManufacturing(int orderId, double time_required, double deadline) {
        this.orderId = orderId;
        this.time_required = time_required;
        this.deadline = deadline;
    }


    //getter and setter

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getTime_required() {
        return time_required;
    }

    public void setTime_required(double time_required) {
        this.time_required = time_required;
    }

    public double getDeadline() {
        return deadline;
    }

    public void setDeadline(double deadline) {
        this.deadline = deadline;
    }
}
