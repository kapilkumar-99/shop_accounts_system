package com.example.shop_accounts_system.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.shop_accounts_system.entity.Salary;


public interface SalaryRepository extends CrudRepository<Salary,Integer>{

    Optional<Salary> findByStaffId(int parseInt);
    
}
