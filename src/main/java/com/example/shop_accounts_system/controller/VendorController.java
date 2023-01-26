package com.example.shop_accounts_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_accounts_system.dto.AddVendorRequest;
import com.example.shop_accounts_system.dto.VendorDTO;
import com.example.shop_accounts_system.service.VendorService;

@RestController
@RequestMapping("/admin")
public class VendorController {

    @Autowired
    VendorService vendorService;
    
    @PostMapping("/vendor")
    public ResponseEntity<VendorDTO> addVendor(@RequestBody AddVendorRequest addVendorRequest) throws Exception{
        VendorDTO vendorDTO = vendorService.addVendor(addVendorRequest);
        return new ResponseEntity<>(vendorDTO, HttpStatus.CREATED);
    }
}
