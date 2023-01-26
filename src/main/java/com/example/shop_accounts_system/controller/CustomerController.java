package com.example.shop_accounts_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_accounts_system.dto.AddCustomerRequest;
import com.example.shop_accounts_system.dto.CustomerDTO;
import com.example.shop_accounts_system.service.CustomerService;

@RestController
public class CustomerController {
    
    @Autowired
    CustomerService customerService;
    
    @PostMapping("/admin/customer")
    public ResponseEntity<CustomerDTO> addCoustomer(@RequestBody AddCustomerRequest addCustomerRequest) throws Exception{
        CustomerDTO customerDTO = customerService.addCustomer(addCustomerRequest);
        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }
}
