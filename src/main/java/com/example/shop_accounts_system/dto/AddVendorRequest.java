package com.example.shop_accounts_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddVendorRequest {
    private int shopId;
    private String name;
    private String address;
    private String cnic;
    private String phoneNumber;
    private String due;

    public void setShop(int shopId){
        this.shopId = shopId;
    }
}
