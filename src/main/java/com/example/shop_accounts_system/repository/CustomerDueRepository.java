package com.example.shop_accounts_system.repository;


import org.springframework.data.repository.CrudRepository;

import com.example.shop_accounts_system.entity.CustomerDue;

public interface CustomerDueRepository extends CrudRepository<CustomerDue, Integer>{
    
}
