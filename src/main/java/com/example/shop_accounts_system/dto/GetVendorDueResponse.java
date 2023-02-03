package com.example.shop_accounts_system.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetVendorDueResponse {
    private int id;
    private int vendorId;
    private int dueClearAmount;
    private Date date;
}
