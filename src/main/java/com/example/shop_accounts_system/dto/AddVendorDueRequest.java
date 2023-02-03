package com.example.shop_accounts_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddVendorDueRequest {
    private int vendorId;
    private int accountId;
    private int dueClearAmount;
    private String date;
}
