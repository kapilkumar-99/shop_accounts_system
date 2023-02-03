package com.example.shop_accounts_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_accounts_system.dto.AccountDTO;
import com.example.shop_accounts_system.dto.AddAcountRequest;
import com.example.shop_accounts_system.dto.GetAccountResponse;
import com.example.shop_accounts_system.dto.UpdateAccountRequest;
import com.example.shop_accounts_system.service.AccountService;

@RestController
@RequestMapping("/admin")
public class AccountController {
    
    @Autowired
    AccountService accountService;

    @PostMapping("/add/account")
    public ResponseEntity<AccountDTO> addAccount(@RequestBody AddAcountRequest addAcountRequest){
        AccountDTO accountDTO = accountService.addAccount(addAcountRequest);
        return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.CREATED);
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable String id, @RequestBody UpdateAccountRequest request) throws Exception {
        System.out.println("updating Account");
        AccountDTO accountDTO = accountService.updateAccount(id, request);
        return new ResponseEntity<> (accountDTO, HttpStatus.OK);
    }

    @GetMapping("/get/account/{id}")
    public GetAccountResponse getAccountById(@PathVariable String id) throws Exception{
        GetAccountResponse account = accountService.getAccountById(id);
        return account;
    }

    @GetMapping("/get/accounts")
    public List<GetAccountResponse> findAllAccount(){
        List <GetAccountResponse> accounts = accountService.findAllAccount();
        return accounts;
    }

    @DeleteMapping("/delete/account/{id}")
    public void deleteById(@PathVariable String id)throws Exception{
        accountService.accountDeleteById(id);
    }
}
