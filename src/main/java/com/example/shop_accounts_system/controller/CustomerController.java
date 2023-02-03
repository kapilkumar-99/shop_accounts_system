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

import com.example.shop_accounts_system.dto.AddCustomerRequest;
import com.example.shop_accounts_system.dto.CustomerDTO;
import com.example.shop_accounts_system.dto.GetCustomerResponse;
import com.example.shop_accounts_system.dto.UpdateCustomerRequest;
import com.example.shop_accounts_system.service.CustomerService;

@RestController
@RequestMapping("/admin")
public class CustomerController {
    
    @Autowired
    CustomerService customerService;
    
    @PostMapping("/customer")
    public ResponseEntity<CustomerDTO> addCoustomer(@RequestBody AddCustomerRequest addCustomerRequest) throws Exception{
        CustomerDTO customerDTO = customerService.addCustomer(addCustomerRequest);
        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable String id, @RequestBody UpdateCustomerRequest request) throws Exception {
        System.out.println("updating Customer");
        CustomerDTO customerDTO = customerService.updateCustomer(id, request);
        return new ResponseEntity<> (customerDTO, HttpStatus.OK);
    }

    @GetMapping("/get/customer/{id}")
    public GetCustomerResponse getVendorById(@PathVariable String id) throws Exception{
        GetCustomerResponse customer = customerService.getCustomerById(id);
        return customer;
    }

    @GetMapping("/get/customers")
    public List<GetCustomerResponse> findAllCustomer(){
        List <GetCustomerResponse> customers = customerService.findAllCustomer();
        return customers;
    }

    @DeleteMapping("/delete/customer/{id}")
    public void vendorDeleteById(@PathVariable String id)throws Exception{
        customerService.customerDeleteById(id);
    }
}
