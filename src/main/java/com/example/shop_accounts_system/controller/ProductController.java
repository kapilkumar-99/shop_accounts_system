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

import com.example.shop_accounts_system.dto.AddProductRequest;
import com.example.shop_accounts_system.dto.GetProductResponse;
import com.example.shop_accounts_system.dto.ProductDTO;
import com.example.shop_accounts_system.dto.UpdateProductRequest;
import com.example.shop_accounts_system.service.ProductService;

@RestController
@RequestMapping("/admin")
public class ProductController {
    
    @Autowired
    ProductService productService;
    
    
    @PostMapping("/add/product")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody AddProductRequest addProductRequest){
        ProductDTO productDTO = productService.addProduct(addProductRequest);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String id, @RequestBody UpdateProductRequest request) throws Exception {
        System.out.println("updating product");
        ProductDTO productDTO = productService.updateProduct(id, request);
        return new ResponseEntity<> (productDTO, HttpStatus.OK);
    }

    @GetMapping("/get/product/{id}")
    public GetProductResponse getProductById(@PathVariable String id) throws Exception{
        GetProductResponse product = productService.getProductById(id);
        return product;
    }

    @GetMapping("/get/products")
    public List<GetProductResponse> findAllProduct(){
        List <GetProductResponse> products = productService.findAllProduct();
        return products;
    }

    @DeleteMapping("/delete/product/{id}")
    public void deleteById(@PathVariable String id)throws Exception{
        productService.ProductDeleteById(id);
    }
}
