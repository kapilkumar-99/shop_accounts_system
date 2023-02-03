package com.example.shop_accounts_system.dto;

import java.util.Date;

import com.example.shop_accounts_system.entity.CustomerDue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDueDTO {
    private int id;
    private int customerId;
    private int dueClearAmount;
    private Date date;
    private int accountId;

    public static CustomerDueDTO fromEntity(CustomerDue customerDue){
        CustomerDueDTO customerDueDTO = new CustomerDueDTO();
        customerDueDTO.setId(customerDue.getId());
        customerDueDTO.setCustomerId(customerDue.getCustomer().getId());
        customerDueDTO.setAccountId(customerDue.getAccount().getId());
        customerDueDTO.setDueClearAmount(customerDue.getDueClearAmount());
        customerDueDTO.setDate(customerDue.getDate());
        return customerDueDTO;
    }
}
