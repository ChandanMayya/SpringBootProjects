package com.example.product.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Table(name = "PRODUCT")
@AllArgsConstructor

public class Product {
    @Column(name = "ID")
    private int id;
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "PRODUCT_DESCRIPTION")
    private String productDescription;
    @Column(name = "PRICE")
    private double price;
}
