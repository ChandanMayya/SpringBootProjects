package com.example.stud.service;

import com.example.stud.entity.Bank;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankService {
    public Bank createAccount(Bank bank);

    public List<Bank> getAccounts();

    public Bank getAccount(long id);

    public String creditAccount(long id, float amount);

    public boolean debitAmount(long id,float amount);

    public Boolean checkPassword(long id, String password);

}
