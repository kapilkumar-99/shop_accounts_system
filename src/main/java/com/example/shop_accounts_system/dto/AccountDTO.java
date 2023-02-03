package com.example.shop_accounts_system.dto;

import com.example.shop_accounts_system.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private int id;
    private String bankName;
    private int balance;

    public static AccountDTO fromEntity(Account account){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setBankName(account.getBankName());
        accountDTO.setBalance(account.getBalance());
        return accountDTO;
    }
}
