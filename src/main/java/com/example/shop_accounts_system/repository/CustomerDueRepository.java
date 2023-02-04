package com.example.shop_accounts_system.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.shop_accounts_system.entity.Customer;
import com.example.shop_accounts_system.entity.CustomerDue;

public interface CustomerDueRepository extends CrudRepository<CustomerDue, Integer>{

    CustomerDue findByCustomerId(int parseInt);
    
}
