package com.example.shop_accounts_system.dto;

import com.example.shop_accounts_system.entity.Product;

import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private String name;
    private int currentPrice;
    private int quantity;

    public static ProductDTO fromEntity(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCurrentPrice(product.getCurrentPrice());
        productDTO.setQuantity(product.getQuantity());
        return productDTO;
    }
}
