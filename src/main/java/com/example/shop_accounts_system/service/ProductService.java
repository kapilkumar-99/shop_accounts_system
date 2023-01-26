package com.example.shop_accounts_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop_accounts_system.dto.AddProductRequest;
import com.example.shop_accounts_system.dto.ProductDTO;
import com.example.shop_accounts_system.entity.Product;
import com.example.shop_accounts_system.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    ProductRepository productRepository;

    public ProductDTO addProduct(AddProductRequest addProductRequest){
        Product product = Product.toEntity(addProductRequest);
        Product newProduct = productRepository.save(product);
        return ProductDTO.fromEntity(newProduct);
    }
}
