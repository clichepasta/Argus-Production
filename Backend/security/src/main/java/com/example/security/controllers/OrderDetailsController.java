package com.example.security.controllers;


import com.example.security.entities.OrderDetails;
import com.example.security.entities.OrderedProduct;
import com.example.security.services.OrderService;
import com.example.security.services.ScheduleService;
import com.example.security.services.WorkFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class OrderDetailsController {

    private final OrderService orderService;
    private final ScheduleService scheduleService;
    private final WorkFlowService workFlowService;

    @Autowired
    public OrderDetailsController(OrderService orderService, ScheduleService scheduleService, WorkFlowService workFlowService) {
        this.orderService = orderService;
        this.scheduleService = scheduleService;
        this.workFlowService = workFlowService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<OrderDetails>> getAllOrders(){
        List<OrderDetails> orders = orderService.findAllOrder();
//        List<OrderDetails> demo = orderService.getSortedOrderDetails();

        return  new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<OrderDetails> addOrders(@RequestBody OrderDetails orderDetails){
        OrderDetails new_order = orderService.addOrder(orderDetails);
        return  new ResponseEntity<>(new_order, HttpStatus.CREATED);
    }

    @GetMapping("/updated")
    public ResponseEntity<List<OrderDetails>> getUpdatedList(){
        List<OrderDetails> orders = orderService.getUpdatedList();

        // Update the status to 1 for each retrieved OrderDetails
        for (OrderDetails order : orders) {
            order.setStatus(1); // Set status to 1
        }

        // Save the updated OrderDetails to the database
        for (OrderDetails order : orders) {
            orderService.updateOrderStatus(order); // Implement this method in your service
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @GetMapping("/performSchedule")
    public ResponseEntity<List<OrderDetails>> performSchedule(){
        scheduleService.performScheduledTask();
//        List<OrderDetails> demo = orderService.getSortedOrderDetails();

        return  new ResponseEntity<>( HttpStatus.OK);
    }
    @GetMapping("/workflow")
    public ResponseEntity<List<OrderedProduct>> showWorkFlow(){
        List<OrderedProduct> orderedProducts=workFlowService.getWorkFlow();
//        List<OrderDetails> demo = orderService.getSortedOrderDetails();

        return  new ResponseEntity<>( orderedProducts,HttpStatus.OK);
    }

    @GetMapping("/showRejectedOrders")
    public ResponseEntity<List<OrderDetails>> showRejectedProducts(){
        workFlowService.getWorkFlow();
//        List<OrderDetails> demo = orderService.getSortedOrderDetails();

        return  new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/orderedProduct")
    public ResponseEntity<List<OrderedProduct>>  getOrderedProduct(){
        List<OrderedProduct> orderedProducts = orderService.findAllOrderedProduct();
        return  new ResponseEntity<>(orderedProducts, HttpStatus.CREATED);
    }

    @PostMapping("/orderedProduct")
    public ResponseEntity<OrderedProduct> addOrderedProduct(@RequestBody OrderedProduct orderedProduct){
        OrderedProduct new_ordered = orderService.addOrderedProduct(orderedProduct);
        return  new ResponseEntity<>(new_ordered, HttpStatus.CREATED);
    }


}
