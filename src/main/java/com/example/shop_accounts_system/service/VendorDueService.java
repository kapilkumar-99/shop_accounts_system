package com.example.shop_accounts_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shop_accounts_system.dto.AddVendorDueRequest;
import com.example.shop_accounts_system.dto.GetVendorDueResponse;
import com.example.shop_accounts_system.dto.UpdateVendorDueRequest;
import com.example.shop_accounts_system.dto.VendorDueDTO;
import com.example.shop_accounts_system.entity.Account;
import com.example.shop_accounts_system.entity.Vendor;
import com.example.shop_accounts_system.entity.VendorDue;
import com.example.shop_accounts_system.repository.AccountRepository;
import com.example.shop_accounts_system.repository.VendorDueRepository;
import com.example.shop_accounts_system.repository.VendorRepository;

@Service
public class VendorDueService {
    
    @Autowired
    VendorDueRepository vendorDueRepository;

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    AccountRepository accountRepository;


    @Transactional
    public VendorDueDTO addVendorDue(AddVendorDueRequest addVendorDueRequest) throws Exception {
        Vendor vendor = vendorRepository.findById(addVendorDueRequest.getVendorId())
                                        .orElseThrow(()-> new Exception("Vendor was not found with id "+ addVendorDueRequest.getVendorId()));
        Account account = accountRepository.findById(addVendorDueRequest.getAccountId())
                                           .orElseThrow(()-> new Exception("Account was not fount with id"+ addVendorDueRequest.getAccountId()));
        
        int duePaidAmount = addVendorDueRequest.getDueClearAmount(); 
        if(addVendorDueRequest.getDueClearAmount() > vendor.getDue()){
            throw new Exception("Due clearing amount is more than remaining due, and due amount is "+vendor.getDue());
        }
        vendor.setDue(vendor.getDue()-duePaidAmount);
        vendorRepository.save(vendor);  
        
        account.setBalance(account.getBalance()-duePaidAmount);
        accountRepository.save(account);
        
        VendorDue vendorDue = VendorDue.toEntity(addVendorDueRequest, vendor, account);
        VendorDue newVendorDue = vendorDueRepository.save(vendorDue);
        return VendorDueDTO.fromEntity(newVendorDue);
    }

    @Transactional
    public VendorDueDTO updateVendorDue(String vendorId, UpdateVendorDueRequest request) throws Exception {
        Account account = accountRepository.findById(request.getAccountId())
                                        .orElseThrow(()-> new Exception("Account was not found with id "+ request.getAccountId()));
        
        try {
            VendorDue existingVendorDue = vendorDueRepository.findByVendorId(Integer.parseInt(vendorId));

            account.setBalance(account.getBalance()-request.getDueClearAmount());
            accountRepository.save(account);

            if(request.getDueClearAmount() > 0) {
                existingVendorDue.setDueClearAmount(request.getDueClearAmount());
            }
            if(request.getDate() != null) {
                existingVendorDue.setDate(request.getDate());
            }

            VendorDue updatedVendorDue = vendorDueRepository.save(existingVendorDue);

            return VendorDueDTO.fromEntity(updatedVendorDue);
        } catch (Exception e) {
            throw new Exception("Vendor was not found with id "+vendorId);
        }
    }

    public GetVendorDueResponse getVendorDueById(String id) throws Exception{
        VendorDue vendorDue = vendorDueRepository.findById(Integer.parseInt(id)).orElseThrow(()-> new Exception("Vendor due was not found with id "+id));
       GetVendorDueResponse getVendorDue = new GetVendorDueResponse(vendorDue.getId(),vendorDue.getVendor().getId(),vendorDue.getDueClearAmount(),vendorDue.getDate());
       return getVendorDue;
    }

    public List<GetVendorDueResponse> findAllVendorDue(){
        List<VendorDue> vendorDues = (List<VendorDue>) vendorDueRepository.findAll();
        List<GetVendorDueResponse> allVendorDues = new ArrayList<>();
        for(VendorDue vendorDue: vendorDues){
            GetVendorDueResponse getVendorDueResponse = new GetVendorDueResponse(vendorDue.getId(),vendorDue.getVendor().getId(),vendorDue.getDueClearAmount(),vendorDue.getDate());
            allVendorDues.add(getVendorDueResponse);
        }
        return allVendorDues;
    }
}
