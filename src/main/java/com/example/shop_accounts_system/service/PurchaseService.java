package com.example.shop_accounts_system.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.shop_accounts_system.dto.AddPurchaseRequest;
import com.example.shop_accounts_system.dto.PurchaseDTO;
import com.example.shop_accounts_system.entity.Product;
import com.example.shop_accounts_system.entity.Purchase;
import com.example.shop_accounts_system.entity.Shop;
import com.example.shop_accounts_system.entity.Vendor;
import com.example.shop_accounts_system.repository.ProductRepository;
import com.example.shop_accounts_system.repository.PurchaseRepository;
import com.example.shop_accounts_system.repository.ShopRepository;
import com.example.shop_accounts_system.repository.VendorRepository;

@Service
public class PurchaseService {
    
    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    ProductRepository productRepository;

    public PurchaseDTO addPurchase(AddPurchaseRequest addPurchaseRequest) throws Exception{
        Shop shop = shopRepository.findById(addPurchaseRequest.getShopId())
                                  .orElseThrow(()-> new Exception("Shop was not found with id "+ addPurchaseRequest.getShopId()));
        Vendor vendor = vendorRepository.findById(addPurchaseRequest.getVendorId())
                                        .orElseThrow(()-> new Exception("Vendor was not found with id "+ addPurchaseRequest.getVendorId()));
        Product product = productRepository.findById(addPurchaseRequest.getProductId())
                                            .orElseThrow(()-> new Exception("Product was not found with id "+ addPurchaseRequest.getProductId()));
        Purchase purchase = Purchase.toEntity(addPurchaseRequest,shop,vendor,product);
        Purchase newPurchase = purchaseRepository.save(purchase);
        return PurchaseDTO.fromEntity(newPurchase);
    }
}
