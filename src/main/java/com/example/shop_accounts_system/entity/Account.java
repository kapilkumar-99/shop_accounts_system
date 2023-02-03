package com.example.shop_accounts_system.entity;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.shop_accounts_system.dto.AddAcountRequest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(generator = "increment")
    private int id;
    private String bankName;
    private int balance;

    @OneToMany(mappedBy = "account")
    private List<Purchase> purchase;

    @OneToMany(mappedBy = "account")
    private List<Salary> salary;

    @OneToMany(mappedBy = "account")
    private List<Sell> sell;

    @OneToMany(mappedBy = "account")
    private List<VendorDue> vendorDue;

    @OneToMany(mappedBy = "account")
    private List<CustomerDue> customerDues;

    @OneToMany(mappedBy = "account")
    private List<MiscellaneousExpenses> miscellaneousExpenses;

    public static Account toEntity(AddAcountRequest addAcountRequest){
        Account account = new Account();
        account.setBankName(addAcountRequest.getBankName());
        account.setBalance(addAcountRequest.getBalance());
        return account;
    }
}
