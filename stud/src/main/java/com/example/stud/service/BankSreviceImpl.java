package com.example.stud.service;

import com.example.stud.entity.Bank;
import com.example.stud.repositroy.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BankSreviceImpl implements BankService{

    @Autowired
    BankRepository bankRepository;
    @Override
    public Bank createAccount(Bank bank) {
        return (Bank) bankRepository.save(bank);
    }

    @Override
    public List<Bank> getAccounts() {
        return bankRepository.findAll();
    }

    @Override
    public Bank getAccount(long id) {
        return bankRepository.findById(id).orElse(null);
    }

    @Override
    public String creditAccount(long id, float amount) {
        if(amount>0){
            Bank account=bankRepository.findById(id).orElse(null);
            float existingBalance= account.getBalance();
            amount=existingBalance+amount;
            account.setBalance(amount);
            bankRepository.save(account);
            return "Success";

        }
        return "Fail";
    }

    @Override
    public boolean debitAmount(long id, float amount) {
        if(amount>0){
            Bank account=bankRepository.findById(id).orElse(null);
            float exixtingBalance=account.getBalance();
            if((exixtingBalance-amount)>=500){
                amount=exixtingBalance-amount;
                account.setBalance(amount);
                bankRepository.save(account);
                return true;
            }
        }return  false;
    }

    @Override
    public Boolean checkPassword(long id, String password) {
        Bank account=bankRepository.findById(id).orElse(null);
        if((account.getPassword().equals(password)))
            return  Boolean.TRUE;
        else
            return Boolean.FALSE;
    }


}

