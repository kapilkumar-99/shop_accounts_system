package com.example.shop_accounts_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPurchaseRequest {
    private int shopId;
    private int vendorId;
    private int productId;
    private String dateOfClearing;
    private int accountId;
    private int itemQuantity;
    private int dueAmount;
    private int totalAmount;
}
