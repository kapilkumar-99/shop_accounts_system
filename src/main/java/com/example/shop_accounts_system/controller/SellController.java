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

import com.example.shop_accounts_system.dto.AddSellRequest;
import com.example.shop_accounts_system.dto.GetSellResponse;
import com.example.shop_accounts_system.dto.SellDTO;
import com.example.shop_accounts_system.service.SellService;

@RestController
@RequestMapping("/admin")
public class SellController {

    @Autowired
    SellService sellService;

    @PostMapping("/add/sell")
    public ResponseEntity<SellDTO> addSell(@RequestBody AddSellRequest addSellRequest) throws Exception{
       SellDTO sellDTO = sellService.addSell(addSellRequest);
        return new ResponseEntity<>(sellDTO,HttpStatus.CREATED);
    }

    @GetMapping("/get/sell/{id}")
    public GetSellResponse getSellById(@PathVariable String id) throws Exception{
        GetSellResponse sell = sellService.getSellById(id);
        return sell;
    }

    @GetMapping("/get/sells")
    public List<GetSellResponse> findAllVendor(){
        List <GetSellResponse> sells = sellService.findAllSell();
        return sells;
    }
}
