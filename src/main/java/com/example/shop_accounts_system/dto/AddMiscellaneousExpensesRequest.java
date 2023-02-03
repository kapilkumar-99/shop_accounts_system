package com.example.shop_accounts_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMiscellaneousExpensesRequest {
    private int accountId;
    private String category;
    private int amount;
    private String date;
}
