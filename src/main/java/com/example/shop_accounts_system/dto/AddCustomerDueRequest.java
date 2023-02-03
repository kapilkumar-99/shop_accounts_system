package com.example.shop_accounts_system.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCustomerDueRequest {
    private int customerId;
    private int accountId;
    private int dueClearAmount;
    private Date date;
}
