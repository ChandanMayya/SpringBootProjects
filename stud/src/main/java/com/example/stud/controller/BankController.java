package com.example.stud.controller;

import com.example.stud.entity.Bank;
import com.example.stud.service.BankService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    BankService bankService;

    @RequestMapping("/")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bankHome");
        return modelAndView;
    }

    @RequestMapping("/create_account")
    public ModelAndView create(Model model, HttpSession session) {
        Bank bank = new Bank();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create_account");
        model.addAttribute("bank", bank);
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @PostMapping("/loginVerify")
    public ModelAndView loginVerify(HttpServletRequest request, Model model, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("account");
        long Id = Long.parseLong(request.getParameter("id"));
        String password = request.getParameter("password");
        Boolean flag = bankService.checkPassword(Id, password);
        if (flag == Boolean.TRUE) {
            model.addAttribute("account", bankService.getAccount(Id));
            session.setAttribute("id", Id);
            return modelAndView;
        }
        return null;
    }

    @PostMapping("/save-account")
    public ModelAndView saveAccount(@ModelAttribute("bank") Bank bank) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bank");
        bankService.createAccount(bank);
        return modelAndView;
    }

    @GetMapping("/bankdisplay")
    public ModelAndView showAccounts(Model model) {
        model.addAttribute("bank", bankService.getAccounts());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bank");
        return modelAndView;
    }

    @PostMapping("/account")
    public ModelAndView getAccount(Model model, HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("account");
        HttpSession session = request.getSession();
        session.setAttribute("id", id);
        model.addAttribute("account", bankService.getAccount(id));
        return modelAndView;
    }

    @PostMapping("/creditmenu")
    public ModelAndView credit(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        long id = (long) session.getAttribute("id");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("credit");
        return modelAndView;
    }

    @GetMapping("/creditamt")
    public ModelAndView handleCreditAmount(HttpServletRequest request, Model model) {


        HttpSession session = request.getSession();
        Float amount = Float.parseFloat(request.getParameter("amount"));
        Long id = (long) session.getAttribute("id");
        String responce = bankService.creditAccount(id, amount);
        model.addAttribute("account", bankService.getAccount(id));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("account");
        return modelAndView;
    }

    @PostMapping("/debitmenu")
    public ModelAndView debit(HttpServletRequest request, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("debit");
        HttpSession session = request.getSession();
        return modelAndView;
    }

    @GetMapping("/debitAmount")
    public ModelAndView handleDebitAmount(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        long id = (long) session.getAttribute("id");
        float amount = Float.parseFloat(request.getParameter("amount"));
        bankService.debitAmount(id, amount);
        model.addAttribute("account", bankService.getAccount(id));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("account");
        return modelAndView;
    }
}
