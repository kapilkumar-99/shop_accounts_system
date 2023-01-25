package com.example.shop_accounts_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateShopRequest {
    private String name;
    private String address;
    private String phoneNumber;
}
