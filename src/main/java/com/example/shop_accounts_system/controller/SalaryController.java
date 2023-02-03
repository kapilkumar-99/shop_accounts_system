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

import com.example.shop_accounts_system.dto.AddSalaryRequest;
import com.example.shop_accounts_system.dto.GetSalaryResponse;
import com.example.shop_accounts_system.dto.SalaryDTO;
import com.example.shop_accounts_system.service.SalaryService;

@RestController
@RequestMapping("/admin")
public class SalaryController {
    
    @Autowired
    SalaryService salaryService;

    @PostMapping("/add/salary")
    public ResponseEntity<SalaryDTO> addSalary(@RequestBody AddSalaryRequest addSalaryRequest) throws Exception{
        SalaryDTO salaryDTO = salaryService.addSalary(addSalaryRequest);
        return new ResponseEntity<>(salaryDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get/salary/{id}")
    public GetSalaryResponse getSalaryById(@PathVariable String id) throws Exception{
        GetSalaryResponse salary = salaryService.getSalaryById(id);
        return salary;
    }

    @GetMapping("/get/salaries")
    public List<GetSalaryResponse> findAllSalary(){
        List <GetSalaryResponse> salaries = salaryService.findAllSalary();
        return salaries;
    }
}
