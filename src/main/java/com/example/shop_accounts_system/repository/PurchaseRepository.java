package com.example.shop_accounts_system.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.shop_accounts_system.entity.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer>{
    
}
