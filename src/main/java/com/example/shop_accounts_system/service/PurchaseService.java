package com.example.shop_accounts_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.shop_accounts_system.dto.AddPurchaseRequest;
import com.example.shop_accounts_system.dto.GetPurchaseResponse;
import com.example.shop_accounts_system.dto.PurchaseDTO;
import com.example.shop_accounts_system.entity.Account;
import com.example.shop_accounts_system.entity.Product;
import com.example.shop_accounts_system.entity.Purchase;
import com.example.shop_accounts_system.entity.Shop;
import com.example.shop_accounts_system.entity.Vendor;
import com.example.shop_accounts_system.exception_handling.NotFoundException;
import com.example.shop_accounts_system.repository.AccountRepository;
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

    @Autowired
    AccountRepository accountRepository;

    public PurchaseDTO addPurchase(AddPurchaseRequest addPurchaseRequest) throws Exception{
        Shop shop = shopRepository.findById(addPurchaseRequest.getShopId())
                                  .orElseThrow(()-> new NotFoundException("Shop was not found with id "+ addPurchaseRequest.getShopId()));
        Vendor vendor = vendorRepository.findById(addPurchaseRequest.getVendorId())
                                        .orElseThrow(()-> new NotFoundException("Vendor was not found with id "+ addPurchaseRequest.getVendorId()));
        Product product = productRepository.findById(addPurchaseRequest.getProductId())
                                           .orElseThrow(()-> new NotFoundException("Product was not found with id "+ addPurchaseRequest.getProductId()));   
        Account account = accountRepository.findById(addPurchaseRequest.getAccountId())
                                           .orElseThrow(()-> new NotFoundException("Account was not found with id "+addPurchaseRequest.getAccountId()));
        
        int totalAmount = addPurchaseRequest.getTotalAmount();
        int due = addPurchaseRequest.getDueAmount();
        int currentPaid= totalAmount-due;
        
        if(account.getBalance() > currentPaid){
            account.setBalance(account.getBalance()-currentPaid);
            accountRepository.save(account);
        }
        else{
            throw new Exception("Balance is insufficient in the account with id "+addPurchaseRequest.getAccountId());
        }

        vendor.setDue(vendor.getDue()+due);
        vendorRepository.save(vendor);
        
        int newQuantity = addPurchaseRequest.getItemQuantity();
        int newPrice = addPurchaseRequest.getTotalAmount();
        int currentQuantity = product.getQuantity();
        int currentPrice = product.getCurrentPrice();
        int meanPrice = (newQuantity*newPrice + currentPrice*currentQuantity)/(newQuantity+currentQuantity);
        int totalQuantity = currentQuantity+newQuantity;
        
        product.setCurrentPrice(meanPrice);
        product.setQuantity(totalQuantity);
        productRepository.save(product);

        
        Purchase purchase = Purchase.toEntity(addPurchaseRequest,shop,vendor,product,account);
        Purchase newPurchase = purchaseRepository.save(purchase);
        return PurchaseDTO.fromEntity(newPurchase);
    }

    public GetPurchaseResponse getPurchaseById(String id) throws Exception{
       Purchase purchase = purchaseRepository.findById(Integer.parseInt(id)).orElseThrow(()-> new Exception("Purchase was not found with id "+id));
       GetPurchaseResponse getPurchase = new GetPurchaseResponse(purchase.getId(), purchase.getShop().getId(), purchase.getVendor().getId(), purchase.getProduct().getId(), purchase.getDateOfClearing(),purchase.getAccount().getId() , purchase.getItemQuantity(), purchase.getDueAmount(), purchase.getTotalAmount());
       return getPurchase;
    }

    public List<GetPurchaseResponse> findAllPurchase(){
        List<Purchase> purchases = (List<Purchase>) purchaseRepository.findAll();
        List<GetPurchaseResponse> allPurchases = new ArrayList<>();
        for(Purchase purchase: purchases){
            GetPurchaseResponse getPurchaseResponse = new GetPurchaseResponse(purchase.getId(), purchase.getShop().getId(), purchase.getVendor().getId(), purchase.getProduct().getId(), purchase.getDateOfClearing(),purchase.getAccount().getId() , purchase.getItemQuantity(), purchase.getDueAmount(), purchase.getTotalAmount());
            allPurchases.add(getPurchaseResponse);
        }
        return allPurchases;
    }
}
