package com.example.shop_accounts_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_accounts_system.dto.AddMiscellaneousExpensesRequest;
import com.example.shop_accounts_system.dto.GetMiscellaneousExpensesResponse;
import com.example.shop_accounts_system.dto.MiscellaneousExpensesDTO;
import com.example.shop_accounts_system.service.MiscellaneousExpensesService;

@RestController
@RequestMapping("/admin")
public class MiscellaneousExpensesController {
    
    @Autowired
    MiscellaneousExpensesService miscellaneousExpensesService;

    @PostMapping("/add/expense")
    public ResponseEntity<MiscellaneousExpensesDTO> addExpenses(@RequestBody AddMiscellaneousExpensesRequest addMiscellaneousExpensesRequest) throws Exception{
        MiscellaneousExpensesDTO miscellaneousExpensesDTO = miscellaneousExpensesService.addExpenses(addMiscellaneousExpensesRequest);
        return new ResponseEntity<MiscellaneousExpensesDTO>(miscellaneousExpensesDTO, HttpStatus.CREATED);
    }
    @GetMapping("/get/expense/{id}")
    public GetMiscellaneousExpensesResponse getExpenseById(@PathVariable String id) throws Exception{
        GetMiscellaneousExpensesResponse expense = miscellaneousExpensesService.getExpenseById(id);
        return expense;
    }

    @GetMapping("/get/expense")
    public List<GetMiscellaneousExpensesResponse> findAllExpense(){
        List <GetMiscellaneousExpensesResponse> expenses = miscellaneousExpensesService.findAllExpense();
        return expenses;
    }


}
