package com.example.stud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long tansactionId;

    @Column
    public  String transactionType;

    @Column
    public Float transactionAmount;

    @Column
    public  Float balance;
}
