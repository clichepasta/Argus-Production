package com.example.security.services;

import com.example.security.entities.Product;
import com.example.security.repositories.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo){
        this.productRepo=productRepo;
    }

    public Product addProduct(Product product){
        product.setProfit();
        return productRepo.save(product);}

    public List<Product> findAllProducts(){
        return productRepo.findAll();
    }

}
