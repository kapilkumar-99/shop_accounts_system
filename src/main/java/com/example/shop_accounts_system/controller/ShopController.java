package com.example.shop_accounts_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_accounts_system.dto.AddCustomerRequest;
import com.example.shop_accounts_system.dto.AddShopRequest;
import com.example.shop_accounts_system.dto.AddVendorRequest;
import com.example.shop_accounts_system.dto.CustomerDTO;
import com.example.shop_accounts_system.dto.ShopDTO;
import com.example.shop_accounts_system.dto.UpdateShopRequest;
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
    public ResponseEntity<ShopDTO> addShop(@RequestBody AddShopRequest addShopRequest) throws Exception {
        ShopDTO shopDTO = shopService.addShop(addShopRequest);
        return new ResponseEntity<> (shopDTO, HttpStatus.CREATED);
    }

    @PutMapping("/admin/shop/{id}")
    public ResponseEntity<ShopDTO> updateShop(@PathVariable String id, @RequestBody UpdateShopRequest request) throws Exception {
        System.out.println("updating shop");
        ShopDTO shopDTO = shopService.updateShop(id, request);
        return new ResponseEntity<> (shopDTO, HttpStatus.OK);
    }

    @PostMapping("/admin/vendor")
    public ResponseEntity<VendorDTO> addVendor(@RequestBody AddVendorRequest addVendorRequest) throws Exception{
        VendorDTO vendorDTO = vendorService.addVendor(addVendorRequest);
        return new ResponseEntity<>(vendorDTO, HttpStatus.CREATED);
    }

    @PostMapping("/admin/customer")
    public ResponseEntity<CustomerDTO> addCoustomer(@RequestBody AddCustomerRequest addCustomerRequest) throws Exception{
        CustomerDTO customerDTO = customerService.addCustomer(addCustomerRequest);
        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }

}
