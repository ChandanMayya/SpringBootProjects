package com.example.product.controller;

import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping
    public String showGreetings(){
        return productService.greet();
    }
    @GetMapping("/list")
    public String listProducts (){
        return productService.fetchProducts().toString();
    }
}
