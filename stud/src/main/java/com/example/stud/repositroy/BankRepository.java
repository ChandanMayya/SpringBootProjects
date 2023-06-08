package com.example.stud.repositroy;

import com.example.stud.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankRepository extends JpaRepository <Bank, Long>{
//
// @Query("select  from bank")
//    List<Bank> findAll();

}
