package com.example.shop_accounts_system.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop_accounts_system.dto.AddVendorRequest;
import com.example.shop_accounts_system.dto.VendorDTO;
import com.example.shop_accounts_system.entity.Shop;
import com.example.shop_accounts_system.entity.Vendor;
import com.example.shop_accounts_system.repository.ShopRepository;
import com.example.shop_accounts_system.repository.VendorRepository;



@Service
public class VendorService {

    @Autowired
    ShopRepository shopRepository;


    @Autowired
    VendorRepository vendorRepository;

    public VendorDTO addVendor(AddVendorRequest addVendorRequest) throws Exception{
        Optional<Shop> shop = shopRepository.findById(addVendorRequest.getShopId());
        if(shop.isPresent()){
            Vendor shopId = new Vendor();
            shopId.setShop(shop.get());
            Vendor vendor = Vendor.toEntity(addVendorRequest);
            Vendor newVendor = vendorRepository.save(vendor);
            return VendorDTO.fromEntity(newVendor);
        }
        else{
            throw new Exception("Shop was not found with "+addVendorRequest.getShopId());
        }
    }
    
}
