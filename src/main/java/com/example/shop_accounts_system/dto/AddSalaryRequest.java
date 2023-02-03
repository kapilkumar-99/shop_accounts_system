package com.example.shop_accounts_system.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSalaryRequest {
    private int accountId;
    private int staffId;
    private String date;
    private int salary;
}
