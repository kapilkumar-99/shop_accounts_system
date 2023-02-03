package com.example.shop_accounts_system.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.shop_accounts_system.dto.AddCustomerDueRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerDue {
    @Id
    @GeneratedValue(generator = "increment")
    private int id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    private int dueClearAmount;
    private Date date;

    public static CustomerDue toEntity(AddCustomerDueRequest addCustomerDueRequest, Customer customer, Account account){
        CustomerDue customerDue = new CustomerDue();
        customerDue.setAccount(account);
        customerDue.setCustomer(customer);
        customerDue.setDate(addCustomerDueRequest.getDate());
        customerDue.setDueClearAmount(addCustomerDueRequest.getDueClearAmount());
        return customerDue;

    }
}
