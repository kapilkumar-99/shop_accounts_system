package com.example.shop_accounts_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_accounts_system.dto.AddCustomerDueRequest;
import com.example.shop_accounts_system.dto.CustomerDueDTO;
import com.example.shop_accounts_system.dto.GetCustomerDueResponse;
import com.example.shop_accounts_system.dto.UpdateCustomerDueRequest;
import com.example.shop_accounts_system.service.CustomerDueService;

@RestController
@RequestMapping("/admin")
public class CustomerDueController {
    
    @Autowired
    CustomerDueService customerDueService;

    @PostMapping("/add/customerdue")
    public ResponseEntity<CustomerDueDTO> addCustomerDue(@RequestBody AddCustomerDueRequest addCustomerDueRequest) throws Exception{
        CustomerDueDTO customerDueDTO = customerDueService.addCustomerDue(addCustomerDueRequest);
        return new ResponseEntity<CustomerDueDTO>(customerDueDTO, HttpStatus.CREATED);
    }

    @PutMapping("/customer/due/{id}")
    public ResponseEntity<CustomerDueDTO> updateCustomerDue(@PathVariable String id, @RequestBody UpdateCustomerDueRequest request) throws Exception {
        System.out.println("updating customer due");
        CustomerDueDTO customerDueDTO = customerDueService.updateCustomerDue(id, request);
        return new ResponseEntity<> (customerDueDTO, HttpStatus.OK);
    }

    @GetMapping("/get/customer/due/{id}")
    public GetCustomerDueResponse getCustomerDueById(@PathVariable String id) throws Exception{
        GetCustomerDueResponse customerDue = customerDueService.getCustomerDueById(id);
        return customerDue;
    }

    @GetMapping("/get/customer/dues")
    public List<GetCustomerDueResponse> findAllCustomerDue(){
        List <GetCustomerDueResponse> customerDues = customerDueService.findAllCustomerDue();
        return customerDues;
    }
}
