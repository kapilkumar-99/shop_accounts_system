package com.example.shop_accounts_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetStaffResponse {
    private int id;
    private String name;
    private String cnic;
    private String phoneNumber;
    private int salary;
    private int loan;
}
