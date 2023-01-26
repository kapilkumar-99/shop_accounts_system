package com.example.shop_accounts_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_accounts_system.dto.AddPurchaseRequest;
import com.example.shop_accounts_system.dto.PurchaseDTO;
import com.example.shop_accounts_system.service.PurchaseService;

@RestController
@RequestMapping("/admin")
public class PurchaseController {
    
    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/addpurchase")
    public ResponseEntity<PurchaseDTO> addPurchase(@RequestBody AddPurchaseRequest addPurchaseRequest) throws Exception{
        PurchaseDTO purchaseDTO = purchaseService.addPurchase(addPurchaseRequest);
        return new ResponseEntity<>(purchaseDTO, HttpStatus.CREATED);
    }
}
