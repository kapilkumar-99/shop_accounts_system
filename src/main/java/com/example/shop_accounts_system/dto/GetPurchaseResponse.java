package com.example.shop_accounts_system.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPurchaseResponse {
    private int id;
    private int shopId;
    private int vendorId;
    private int productId;
    private Date dateOfClearing;
    private int accountId;
    private int itemQuantity;
    private int dueAmount;
    private int totalAmount;
}
