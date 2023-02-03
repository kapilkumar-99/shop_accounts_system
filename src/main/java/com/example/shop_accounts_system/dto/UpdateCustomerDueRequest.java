package com.example.shop_accounts_system.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerDueRequest {
    private int customerId;
    private int dueClearAmount;
    private Date date;
    private int accountId;
}
