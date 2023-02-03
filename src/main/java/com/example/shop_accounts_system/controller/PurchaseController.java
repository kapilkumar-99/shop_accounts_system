package com.example.shop_accounts_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_accounts_system.dto.AddPurchaseRequest;
import com.example.shop_accounts_system.dto.GetPurchaseResponse;
import com.example.shop_accounts_system.dto.PurchaseDTO;
import com.example.shop_accounts_system.service.PurchaseService;

@RestController
@RequestMapping("/admin")
public class PurchaseController {
    
    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/add/purchase")
    public ResponseEntity<PurchaseDTO> addPurchase(@RequestBody AddPurchaseRequest addPurchaseRequest) throws Exception{
        PurchaseDTO purchaseDTO = purchaseService.addPurchase(addPurchaseRequest);
        return new ResponseEntity<>(purchaseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get/purchase/{id}")
    public GetPurchaseResponse getPurchaseById(@PathVariable String id) throws Exception{
        GetPurchaseResponse purchase = purchaseService.getPurchaseById(id);
        return purchase;
    }

    @GetMapping("/get/purchases")
    public List<GetPurchaseResponse> findAllVendor(){
        List <GetPurchaseResponse> purchases = purchaseService.findAllPurchase();
        return purchases;
    }
}
