package com.example.security.services;


import com.example.security.entities.OrderDetails;
import com.example.security.entities.OrderManufacturing;
import com.example.security.repositories.OrderDetailsRepo;
import com.example.security.repositories.OrderedProductRepo;
import com.example.security.repositories.ProductRepo;

import com.example.security.repositories.RejectedOrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

  private final OrderDetailsRepo orderDetailsRepo;
  private final OrderedProductRepo orderedProductRepo;
  private final OrderService orderService;


  @Autowired
  public ScheduleService(ProductRepo productRepo, OrderDetailsRepo orderDetailsRepo, OrderedProductRepo orderedProductRepo, OrderService orderService) {
    this.orderDetailsRepo = orderDetailsRepo;
    this.orderedProductRepo = orderedProductRepo;
    this.orderService = orderService;

  }

  @Scheduled(fixedRate = 600000)
  public void performScheduledTask() {

    List<OrderDetails> ordersBetweenDates = getOrdersBetweenDates();
    for (OrderDetails orderDetails : ordersBetweenDates) {
      int orderId = orderDetails.getOrderId();
      System.out.println("Order ID: " + orderId);
    }


    double time_used, deadline, vacant;
    int order_id, accepted = 1, rejected = -1, id;
    List<OrderManufacturing> orderManufacturing = new ArrayList<>();


    order_id = ordersBetweenDates.get(0).getOrderId();
    time_used = ordersBetweenDates.get(0).getTimeRequired() + 0.6;
    deadline = ordersBetweenDates.get(0).getDeadline();
    vacant = deadline - time_used;
    OrderManufacturing newOrderManufacturing = new OrderManufacturing(order_id, time_used, deadline);
    orderManufacturing.add(newOrderManufacturing);
    System.out.println("Accepted0: " + ordersBetweenDates.get(0).getOrderId() + " - " + time_used + " / " + deadline);


    for (int i = 1; i < ordersBetweenDates.size(); i++) {
      OrderDetails orderDetails = ordersBetweenDates.get(i);
      int currentOrderId = ordersBetweenDates.get(i).getOrderId();
      double next_requiredTime = ordersBetweenDates.get(i).getTimeRequired();
      double next_deadline = ordersBetweenDates.get(i).getDeadline();
      if (i > 0) {
        if (next_deadline < deadline) {
          double specificDeadline_requiredTime = next_requiredTime;
          for (OrderManufacturing j : orderManufacturing) {
            if (j.getDeadline() == next_deadline) {
              specificDeadline_requiredTime = specificDeadline_requiredTime + j.getTime_required();
            }
          }
          if (next_requiredTime <= vacant && specificDeadline_requiredTime <= next_deadline) {
            time_used = time_used + next_requiredTime;
            vacant = vacant - next_requiredTime;


            Optional<OrderDetails> currentOrder = orderDetailsRepo.findById(currentOrderId);
            id = orderDetails.getOrderId();
            orderService.updateStatus(accepted, id);
            OrderManufacturing newOrderManufacturing1 = new OrderManufacturing(currentOrderId, next_requiredTime, next_deadline);
            orderManufacturing.add(newOrderManufacturing1);
            System.out.println("Accepted1: " + ordersBetweenDates.get(i).getOrderId() + " - " + time_used + " / " + deadline);
          } else {

            Optional<OrderDetails> currentOrder = orderDetailsRepo.findById(currentOrderId);
            id = orderDetails.getOrderId();
            orderService.updateStatus(rejected, id);
            System.out.println("Rejected1: " + ordersBetweenDates.get(i).getOrderId() + " - " + time_used + " / " + deadline);
          }
        } else {
          double checkVacant = next_deadline - deadline + vacant;
          if (next_requiredTime <= checkVacant) {
            deadline = next_deadline;
            time_used = time_used + next_requiredTime;
            vacant = deadline - time_used;


            Optional<OrderDetails> currentOrder = orderDetailsRepo.findById(currentOrderId);
            id = orderDetails.getOrderId();
            orderService.updateStatus(accepted, id);
            OrderManufacturing newOrderManufacturing2 = new OrderManufacturing(currentOrderId, next_requiredTime, next_deadline);
            orderManufacturing.add(newOrderManufacturing2);
            System.out.println("Accepted2:" + ordersBetweenDates.get(i).getOrderId() + " - " + time_used + " / " + deadline);
          } else {

            Optional<OrderDetails> currentOrder = orderDetailsRepo.findById(currentOrderId);
            id = orderDetails.getOrderId();
            orderService.updateStatus(rejected, id);

//

            System.out.println("Rejected2:" + ordersBetweenDates.get(i).getOrderId() + " - " + time_used + " / " + deadline);
          }
        }
      } else {
        Optional<OrderDetails> currentOrder = orderDetailsRepo.findById(currentOrderId);
        id = orderDetails.getOrderId();
        orderService.updateStatus(accepted, id);
      }

    }

  }

  public List<OrderDetails> getOrdersBetweenDates() {
    return orderDetailsRepo.findByOrderDateBetween();
  }

  public List<OrderDetails> getOrdersBySortedDeadline() {
    return orderDetailsRepo.findSortedByDeadline();
  }


}
