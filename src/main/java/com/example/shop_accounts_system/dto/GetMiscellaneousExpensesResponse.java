package com.example.shop_accounts_system.dto;

import java.util.Date;

import com.example.shop_accounts_system.entity.ExpensesCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMiscellaneousExpensesResponse {
    private int accountId;
    private ExpensesCategory category;
    private int amount;
    private Date date;
}
