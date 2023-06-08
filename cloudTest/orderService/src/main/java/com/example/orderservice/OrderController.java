package com.example.orderservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ecommerce")
public class OrderController {
    @RequestMapping(value = "/order/product",method = RequestMethod.GET)
    public String order(){
        return "Order Successful";
    }
}
