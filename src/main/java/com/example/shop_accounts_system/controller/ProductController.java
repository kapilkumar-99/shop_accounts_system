package com.example.shop_accounts_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_accounts_system.dto.AddProductRequest;
import com.example.shop_accounts_system.dto.ProductDTO;
import com.example.shop_accounts_system.service.ProductService;

@RestController
@RequestMapping("/admin")
public class ProductController {
    
    @Autowired
    ProductService productService;
    
    
    @PostMapping("/addproduct")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody AddProductRequest addProductRequest){
        ProductDTO productDTO = productService.addProduct(addProductRequest);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }
}
