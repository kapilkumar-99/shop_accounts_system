package com.example.shop_accounts_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shop_accounts_system.dto.AddMiscellaneousExpensesRequest;
import com.example.shop_accounts_system.dto.GetMiscellaneousExpensesResponse;
import com.example.shop_accounts_system.dto.MiscellaneousExpensesDTO;
import com.example.shop_accounts_system.entity.Account;
import com.example.shop_accounts_system.entity.MiscellaneousExpenses;
import com.example.shop_accounts_system.repository.AccountRepository;
import com.example.shop_accounts_system.repository.MiscellaneousExpensesRepository;

@Service
public class MiscellaneousExpensesService {
    
    @Autowired
    MiscellaneousExpensesRepository miscellaneousExpensesRepository;

    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public MiscellaneousExpensesDTO addExpenses(AddMiscellaneousExpensesRequest addMiscellaneousExpensesRequest) throws Exception{
        Account account = accountRepository.findById(addMiscellaneousExpensesRequest.getAccountId())
                                           .orElseThrow(()-> new Exception("Account was not found with id "+
                                           addMiscellaneousExpensesRequest.getAccountId()));
        account.setBalance(account.getBalance()-addMiscellaneousExpensesRequest.getAmount());
        accountRepository.save(account);                                                                                                     
        MiscellaneousExpenses miscellaneousExpenses = MiscellaneousExpenses.toEntity(addMiscellaneousExpensesRequest,account);
        MiscellaneousExpenses newMiscellaneousExpenses = miscellaneousExpensesRepository.save(miscellaneousExpenses);
        return MiscellaneousExpensesDTO.fromEntity(newMiscellaneousExpenses);
    }

    public GetMiscellaneousExpensesResponse getExpenseById(String id) throws Exception{
        MiscellaneousExpenses expenses = miscellaneousExpensesRepository.findById(Integer.parseInt(id))
                                        .orElseThrow(()-> new Exception("Expense was not found with id "+id));
       GetMiscellaneousExpensesResponse miscellaneousExpenses = new GetMiscellaneousExpensesResponse(expenses.getAccount().getId(), 
                                        expenses.getCategory(), expenses.getAmount(),expenses.getDate());
       return miscellaneousExpenses;
    }

    public List<GetMiscellaneousExpensesResponse> findAllExpense(){
        List<MiscellaneousExpenses> expenses = (List<MiscellaneousExpenses>) miscellaneousExpensesRepository.findAll();
        List<GetMiscellaneousExpensesResponse> allExpense = new ArrayList<>();
        for(MiscellaneousExpenses expense: expenses){
            GetMiscellaneousExpensesResponse getExpenses = new GetMiscellaneousExpensesResponse(expense.getAccount().getId(), 
                                                        expense.getCategory(), expense.getAmount(),expense.getDate());
            allExpense.add(getExpenses);
        }
        return allExpense;
    }
}
