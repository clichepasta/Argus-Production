package com.example.security.services;

import com.example.security.entities.OrderDetails;
import com.example.security.entities.OrderedProduct;
import com.example.security.entities.Product;
import com.example.security.repositories.OrderDetailsRepo;
import com.example.security.repositories.OrderedProductRepo;
import com.example.security.repositories.ProductRepo;

import com.example.security.repositories.RejectedOrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkFlowService {

  @Autowired
  ProductRepo productRepo;
  @Autowired
  OrderDetailsRepo orderDetailsRepo;

  @Autowired
  OrderedProductRepo orderedProductRepo;

  @Autowired
  private final RejectedOrdersRepo rejectedOrdersRepo;


  @Autowired
  public WorkFlowService(ProductRepo productRepo, OrderDetailsRepo orderDetailsRepo, OrderedProductRepo orderedProductRepo, RejectedOrdersRepo rejectedOrdersRepo) {
    this.productRepo = productRepo;
    this.orderDetailsRepo = orderDetailsRepo;
    this.orderedProductRepo = orderedProductRepo;
    this.rejectedOrdersRepo = rejectedOrdersRepo;
  }

  public List<OrderDetails> getRejectedOrders() {
    return rejectedOrdersRepo.findRejecteedOrders();
  }

  public void checkConsecutiveProductWithDeadline(OrderedProduct orderedProduct, double timeRequired, double checkVacant) {

    if (timeRequired < checkVacant) {
      orderedProduct.setStatus(1);
      orderedProductRepo.save(orderedProduct);
    }


  }

  public List<OrderedProduct> getWorkFlow() {

    List<OrderedProduct> orderedProducts = orderedProductRepo.getOrderedProductSortedByDeadline(); // Fetch ordered products from the repository
    List<OrderedProduct> finalWorkFlow = new ArrayList<>();
    List<OrderDetails> orderDetails = orderDetailsRepo.findSortedByDeadline();

    List<Product> products = productRepo.getProductList();


    double daysUsed = 0, deadline, newDeadline = 0, vacant, checkVacant;

    int orderSeq = 0;

    int orderIdPointer = orderDetails.get(0).getOrderId();
    int productIdPointer = orderedProducts.get(0).getId().getProductId();

    daysUsed = orderedProducts.get(0).getTimeRequired();
    deadline = orderDetails.get(orderSeq).getDeadline();
    vacant = deadline - daysUsed;
    checkVacant = deadline - orderDetails.get(orderSeq).getTimeRequired();
    System.out.println(orderedProducts.size());

    System.out.println(vacant + "  first vacant");
    System.out.println(checkVacant + "  first checkVacant");
    System.out.println(orderDetails.get(0).getConstraintTime() + "  first constraint time");
    System.out.println(daysUsed + " days used");
    System.out.println(deadline + " deadline");


    if (daysUsed < deadline) {

      orderedProducts.get(0).setStatus(1);
      orderedProductRepo.save(orderedProducts.get(0));
      finalWorkFlow.add(orderedProducts.get(0));

    }
    for (int i = 1; i < orderedProducts.size(); i++) {

      if (orderedProducts.get(i).getStatus() == 1)
        continue;

      if ((orderedProducts.size() - i) < products.size() + 1) {
        OrderedProduct currentObj = orderedProductRepo.getOrderedProductByProductId(productIdPointer, orderedProducts.get(i).getId().getOrderId());


        orderedProducts.get(i).setStatus(1);
        orderedProductRepo.save(orderedProducts.get(i));
        finalWorkFlow.add(orderedProducts.get(i));

        daysUsed = daysUsed + orderedProducts.get(i).getTimeRequired();
        continue;

      }

      OrderedProduct currentObj = orderedProductRepo.getOrderedProductByProductId(orderedProducts.get(i).getId().getProductId(), orderedProducts.get(i).getId().getOrderId());
      OrderedProduct executingObj = orderedProductRepo.getOrderedProductByProductId(productIdPointer, orderedProducts.get(i).getId().getOrderId());


      if (currentObj.getId().getOrderId() != orderIdPointer) {
        orderSeq = orderSeq + 1;
        newDeadline = orderDetails.get(orderSeq).getDeadline();
//                daysUsed = daysUsed + currentObj.getTimeRequired();
        vacant = newDeadline - deadline + vacant - currentObj.getTimeRequired();
        deadline = newDeadline;
        checkVacant = vacant - orderDetails.get(orderSeq).getConstraintTime();

        if (currentObj.getId().getProductId() == executingObj.getId().getProductId()) {
          currentObj.setStatus(1);
          orderedProductRepo.save(currentObj);
          finalWorkFlow.add(currentObj);
          productIdPointer = currentObj.getId().getProductId();
          orderIdPointer = currentObj.getId().getOrderId();
          daysUsed = daysUsed + currentObj.getTimeRequired();
          vacant = vacant - currentObj.getTimeRequired() - productRepo.getChainChangeTime(productIdPointer);


        } else {
          OrderedProduct nextConsecutiveProductObj = orderedProductRepo.getOrderedProductByProductId(productIdPointer, orderDetails.get(orderSeq).getOrderId());
          if (nextConsecutiveProductObj.getTimeRequired() < checkVacant) {
            nextConsecutiveProductObj.setStatus(1);
            orderedProductRepo.save(nextConsecutiveProductObj);
            finalWorkFlow.add(nextConsecutiveProductObj);


            daysUsed = daysUsed + nextConsecutiveProductObj.getTimeRequired();
            vacant = vacant - nextConsecutiveProductObj.getTimeRequired();

            checkVacant = checkVacant - nextConsecutiveProductObj.getTimeRequired();
          }
          currentObj.setStatus(1);
          orderedProductRepo.save(currentObj);
          finalWorkFlow.add(currentObj);

          productIdPointer = currentObj.getId().getProductId();
          orderIdPointer = currentObj.getId().getOrderId();
          daysUsed = daysUsed + currentObj.getTimeRequired();
          vacant = vacant - currentObj.getTimeRequired() - productRepo.getChainChangeTime(productIdPointer);
          continue;

        }
      }
      if (currentObj.getId().getOrderId() == executingObj.getId().getOrderId()) {
        if (currentObj.getId().getProductId() != executingObj.getId().getProductId()) {
          OrderedProduct nextConsecutiveProductObj = orderedProductRepo.getOrderedProductByProductId(productIdPointer, orderDetails.get(orderSeq + 1).getOrderId());

          if (nextConsecutiveProductObj.getTimeRequired() < checkVacant) {
            nextConsecutiveProductObj.setStatus(1);
            orderedProductRepo.save(nextConsecutiveProductObj);
            finalWorkFlow.add(nextConsecutiveProductObj);


            daysUsed = daysUsed + nextConsecutiveProductObj.getTimeRequired();
            vacant = vacant - nextConsecutiveProductObj.getTimeRequired();
            checkVacant = checkVacant - nextConsecutiveProductObj.getTimeRequired();

          }
          currentObj.setStatus(1);
          orderedProductRepo.save(currentObj);
          finalWorkFlow.add(currentObj);

          productIdPointer = currentObj.getId().getProductId();
          orderIdPointer = currentObj.getId().getOrderId();
          daysUsed = daysUsed + currentObj.getTimeRequired();
          vacant = vacant - currentObj.getTimeRequired() - productRepo.getChainChangeTime(productIdPointer);


        }
      }


    }


    System.out.println(finalWorkFlow.size());
    System.out.println(daysUsed);

    return finalWorkFlow;

  }
}
