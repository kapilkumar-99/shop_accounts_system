package com.example.shop_accounts_system.dto;

import java.util.Date;

import com.example.shop_accounts_system.entity.ExpensesCategory;
import com.example.shop_accounts_system.entity.MiscellaneousExpenses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiscellaneousExpensesDTO {
    private int accountId;
    private ExpensesCategory category;
    private int amount;
    private Date date;

    public static MiscellaneousExpensesDTO fromEntity(MiscellaneousExpenses miscellaneousExpenses){
        MiscellaneousExpensesDTO miscellaneousExpensesDTO = new MiscellaneousExpensesDTO();
        miscellaneousExpensesDTO.setAccountId(miscellaneousExpenses.getAccount().getId());
        miscellaneousExpensesDTO.setCategory(miscellaneousExpenses.getCategory());
        miscellaneousExpensesDTO.setAmount(miscellaneousExpenses.getAmount());
        miscellaneousExpensesDTO.setDate(miscellaneousExpenses.getDate());
        return miscellaneousExpensesDTO;
    }
}
