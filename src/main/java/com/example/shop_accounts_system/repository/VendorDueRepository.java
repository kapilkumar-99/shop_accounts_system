package com.example.shop_accounts_system.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.shop_accounts_system.entity.VendorDue;

public interface VendorDueRepository extends CrudRepository<VendorDue, Integer>{

    VendorDue findByVendorId(int parseInt);
    
}
