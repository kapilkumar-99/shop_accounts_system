package com.example.shop_accounts_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_accounts_system.dto.AddCustomerRequest;
import com.example.shop_accounts_system.dto.AddShopRequest;
import com.example.shop_accounts_system.dto.AddVendorRequest;
import com.example.shop_accounts_system.dto.CustomerDTO;
import com.example.shop_accounts_system.dto.ShopDTO;
import com.example.shop_accounts_system.dto.VendorDTO;


import com.example.shop_accounts_system.service.CustomerService;
import com.example.shop_accounts_system.service.ShopService;
import com.example.shop_accounts_system.service.VendorService;

@RestController
public class ShopController {

    @Autowired
    ShopService shopService;

    @Autowired
    VendorService vendorService;

    @Autowired
    CustomerService customerService;

    @PostMapping("/admin/shop")
    public ResponseEntity<Object> addShop(@RequestBody AddShopRequest addShopRequest){
        try {
            ShopDTO shopDTO = shopService.addShop(addShopRequest);
            return new ResponseEntity<> (shopDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<> (e.getMessage(), HttpStatus.BAD_REQUEST);
        }
       
    }

    @PostMapping("/admin/vendor")
    public ResponseEntity<Object> addVendor(@RequestBody AddVendorRequest addVendorRequest){
        try {
            VendorDTO vendorDTO = vendorService.addVendor(addVendorRequest);
            return new ResponseEntity<>(vendorDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/admin/customer")
    public ResponseEntity<Object> addCoustomer(@RequestBody AddCustomerRequest addCustomerRequest){
        try{
            CustomerDTO customerDTO = customerService.addCustomer(addCustomerRequest);
            return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
