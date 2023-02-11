package com.example.shop_accounts_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shop_accounts_system.dto.AddSalaryRequest;
import com.example.shop_accounts_system.dto.GetSalaryResponse;
import com.example.shop_accounts_system.dto.SalaryDTO;
import com.example.shop_accounts_system.entity.Account;
import com.example.shop_accounts_system.entity.Salary;
import com.example.shop_accounts_system.entity.Staff;
import com.example.shop_accounts_system.repository.AccountRepository;
import com.example.shop_accounts_system.repository.SalaryRepository;
import com.example.shop_accounts_system.repository.StaffRepository;

@Service
public class SalaryService {
    
    @Autowired
    SalaryRepository salaryRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public SalaryDTO addSalary(AddSalaryRequest addSalaryRequest) throws Exception{
        Staff staff = staffRepository.findById(addSalaryRequest.getStaffId())
                                     .orElseThrow(()-> new Exception("Staff was not fount with staff id "+addSalaryRequest.getStaffId()));
        Account account = accountRepository.findById(addSalaryRequest.getAccountId())
                                           .orElseThrow(()-> new Exception("Account was not fount with id "+addSalaryRequest.getAccountId()));
        int paidSalary = addSalaryRequest.getSalary();
        account.setBalance(account.getBalance()-paidSalary); 
        accountRepository.save(account);                                  
        Salary salary = Salary.toEntity(addSalaryRequest,staff,account);
        Salary newSalary = salaryRepository.save(salary);
        return SalaryDTO.fromEntity(newSalary);
    }

    public GetSalaryResponse getSalaryById(String id) throws Exception{
        Salary salary = salaryRepository.findById(Integer.parseInt(id)).orElseThrow(()-> new Exception("Salaray was not found with staff id "+id));
       GetSalaryResponse getSalary = new GetSalaryResponse(salary.getId(), salary.getAccount().getId(), salary.getSalary(), salary.getDate(),salary.getStaff().getId());
       return getSalary;
    }

    public List<GetSalaryResponse> findAllSalary(){
        List<Salary> salaries = (List<Salary>) salaryRepository.findAll();
        List<GetSalaryResponse> allSalaries = new ArrayList<>();
        for(Salary salary: salaries){
            GetSalaryResponse getSalaryResponse = new GetSalaryResponse(salary.getId(), salary.getAccount().getId(), salary.getSalary(), salary.getDate(),salary.getStaff().getId());
            allSalaries.add(getSalaryResponse);
        }
        return allSalaries;
    }

}
