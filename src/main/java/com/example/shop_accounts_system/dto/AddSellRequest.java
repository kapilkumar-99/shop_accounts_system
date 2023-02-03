package com.example.shop_accounts_system.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSellRequest {
    private int shopId;
    private int customerId;
    private int productId;
    private int accountId;
    private String dateOfClearance;
    private int sellPrice;
    private int quantity;
    private int due;
    private int totalAmount;
}
