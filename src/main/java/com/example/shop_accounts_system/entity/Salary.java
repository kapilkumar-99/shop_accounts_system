package com.example.shop_accounts_system.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.example.shop_accounts_system.dto.AddSalaryRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Salary {
    @Id
    @GeneratedValue(generator = "increment")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staff;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    private Date date;
    private int salary;

    public static Salary toEntity(AddSalaryRequest addSalaryRequest, Staff staff, Account account) throws Exception{
        Salary salary = new Salary();
        salary.setAccount(account);
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String date = addSalaryRequest.getDate();
            salary.setDate(simpleDateFormat.parse(date));
        } catch (Exception e) {
            throw new Exception("Date formate is invaild please use this formate dd-MM-yyyy");
        }
        // salary.setDate(addSalaryRequest.getDate());
        salary.setSalary(addSalaryRequest.getSalary());
        salary.setStaff(staff);
        return salary;
    }
}
