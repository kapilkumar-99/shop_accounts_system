package com.example.shop_accounts_system.dto;

import com.example.shop_accounts_system.entity.Staff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDTO {
    private String name;
    private String cnic;
    private String phoneNumber;
    private int salary;
    private int loan;

    public static StaffDTO fromEntity(Staff staff){
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setName(staff.getName());
        staffDTO.setCnic(staff.getCnic());
        staffDTO.setPhoneNumber(staff.getPhoneNumber());
        staffDTO.setSalary(staff.getSalary());
        staffDTO.setLoan(staff.getLoan());
        return staffDTO;

    }
}
