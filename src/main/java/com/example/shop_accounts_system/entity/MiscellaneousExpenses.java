package com.example.shop_accounts_system.entity;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.shop_accounts_system.dto.AddMiscellaneousExpensesRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MiscellaneousExpenses {
    @Id
    @GeneratedValue(generator = "increment")
    private int id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Account account;

    @Enumerated(value = EnumType.STRING)
    ExpensesCategory category;

    private int amount;
    private Date date;

    public static MiscellaneousExpenses toEntity(AddMiscellaneousExpensesRequest addMiscellaneousExpensesRequest, Account account) throws Exception{
        MiscellaneousExpenses miscellaneousExpenses = new MiscellaneousExpenses();
        miscellaneousExpenses.setAccount(account);
        try {
            miscellaneousExpenses.setCategory(ExpensesCategory.valueOf(addMiscellaneousExpensesRequest.getCategory()));
        } catch (Exception e) {
            throw new Exception("Please provide category from these following " + Arrays.asList(ExpensesCategory.values()));
        }

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String date = addMiscellaneousExpensesRequest.getDate();
            miscellaneousExpenses.setDate(simpleDateFormat.parse(date));
        } catch (Exception e) {
            throw new Exception("Date formate is invaild please use this formate dd-MM-yyyy");
        }
       
        miscellaneousExpenses.setAmount(addMiscellaneousExpensesRequest.getAmount());
        return miscellaneousExpenses;
    }
}
