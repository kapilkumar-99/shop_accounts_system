package com.example.shop_accounts_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddProductRequest {
    private String name;
    private int currentPrice;
    private int quantity;
}
