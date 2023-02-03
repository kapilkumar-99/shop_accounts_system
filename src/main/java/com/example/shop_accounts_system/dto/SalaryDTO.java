package com.example.shop_accounts_system.dto;

import java.util.Date;

import com.example.shop_accounts_system.entity.Salary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDTO {
    private int id;
    private int staffId;
    private int accountId;
    private Date date;
    private int salary;

    public static SalaryDTO fromEntity(Salary salary){
        SalaryDTO salaryDTO = new SalaryDTO();
        salaryDTO.setId(salary.getId());
        salaryDTO.setStaffId(salary.getStaff().getId());
        salaryDTO.setAccountId(salary.getAccount().getId());
        salaryDTO.setDate(salary.getDate());
        salaryDTO.setSalary(salary.getSalary());
        return salaryDTO;
    }

}
