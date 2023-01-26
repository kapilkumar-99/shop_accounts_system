package com.example.shop_accounts_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_accounts_system.dto.AddShopRequest;
import com.example.shop_accounts_system.dto.ShopDTO;
import com.example.shop_accounts_system.dto.UpdateShopRequest;


import com.example.shop_accounts_system.service.ShopService;

@RestController
@RequestMapping("/admin")
public class ShopController {

    @Autowired
    ShopService shopService;

    @PostMapping("/shop")
    public ResponseEntity<ShopDTO> addShop(@RequestBody AddShopRequest addShopRequest) throws Exception {
        ShopDTO shopDTO = shopService.addShop(addShopRequest);
        return new ResponseEntity<> (shopDTO, HttpStatus.CREATED);
    }

    @PutMapping("/shop/{id}")
    public ResponseEntity<ShopDTO> updateShop(@PathVariable String id, @RequestBody UpdateShopRequest request) throws Exception {
        System.out.println("updating shop");
        ShopDTO shopDTO = shopService.updateShop(id, request);
        return new ResponseEntity<> (shopDTO, HttpStatus.OK);
    }

   


}
