package com.example.security.controllers;

import com.example.security.entities.Product;
import com.example.security.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> products = productService.findAllProducts();
    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    Product new_product = productService.addProduct(product);
    return new ResponseEntity<>(new_product, HttpStatus.CREATED);
  }


}
