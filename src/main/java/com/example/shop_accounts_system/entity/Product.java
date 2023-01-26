package com.example.shop_accounts_system.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.shop_accounts_system.dto.AddProductRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Product {
    @Id
    @GeneratedValue(generator = "increment")
    private int id;
    private String name;
    private int currentPrice;
    private int quantity;

    @OneToMany (mappedBy = "product")
    private List<Purchase> purchase;

    public static Product toEntity(AddProductRequest addProductRequest){
        Product product = new Product();
        product.setName(addProductRequest.getName());
        product.setCurrentPrice(addProductRequest.getCurrentPrice());
        product.setQuantity(addProductRequest.getQuantity());
        return product;
    }
    
}
