package com.example.shop_accounts_system.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetSalaryResponse {
    private int id;
    private int staffId;
    private int accountId;
    private Date date;
    private int salary;
}
