package com.example.product.service;

import com.example.product.DTO.ProductDTO;
import com.example.product.Entity.Product;
import com.example.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;
    public String greet(){
        return "Welcome to product Service";
    }

    public List<Product> fetchProducts(){
        return repository.findAll();
    }

}
