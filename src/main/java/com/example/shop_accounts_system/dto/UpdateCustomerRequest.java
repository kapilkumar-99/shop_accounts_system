package com.example.shop_accounts_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {
    private int shopId;
    private String name;
    private String address;
    private String cnic;
    private String phoneNumber;
    private int dues;
}
