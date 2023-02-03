package com.example.shop_accounts_system.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.example.shop_accounts_system.dto.AddStaffRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Staff {
    @Id
    @GeneratedValue(generator = "increment")
    private int id;
    private String name;
    private String cnic;
    private String phoneNumber;
    private int salary;
    private int loan;
    
    @OneToOne(fetch = FetchType.LAZY)
    private Salary salaryPerMonth;

    public static Staff toEntity(AddStaffRequest addStaffRequest){
        Staff staff = new Staff();
        staff.setName(addStaffRequest.getName());
        staff.setCnic(addStaffRequest.getCnic());
        staff.setPhoneNumber(addStaffRequest.getPhoneNumber());
        staff.setSalary(addStaffRequest.getSalary());
        staff.setLoan(addStaffRequest.getLoan());
        return staff; 
    }
}
