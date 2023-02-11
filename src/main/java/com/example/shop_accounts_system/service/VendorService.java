package com.example.shop_accounts_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shop_accounts_system.dto.AddVendorRequest;
import com.example.shop_accounts_system.dto.GetVendorResponse;
import com.example.shop_accounts_system.dto.UpdateVendorRequest;
import com.example.shop_accounts_system.dto.VendorDTO;
import com.example.shop_accounts_system.entity.Shop;
import com.example.shop_accounts_system.entity.Vendor;
import com.example.shop_accounts_system.exception_handling.NotFoundException;
import com.example.shop_accounts_system.exception_handling.DeleteException;
import com.example.shop_accounts_system.repository.ShopRepository;
import com.example.shop_accounts_system.repository.VendorRepository;



@Service
public class VendorService {

    @Autowired
    ShopRepository shopRepository;


    @Autowired
    VendorRepository vendorRepository;

    public VendorDTO addVendor(AddVendorRequest addVendorRequest) throws Exception{
        Shop shop = shopRepository.findById(addVendorRequest.getShopId())
                                  .orElseThrow(()-> new Exception("Shop was not found with id "+ addVendorRequest.getShopId()));        
        Vendor vendor = Vendor.toEntity(addVendorRequest, shop);
        Vendor newVendor = vendorRepository.save(vendor);
        return VendorDTO.fromEntity(newVendor);
    }

    public VendorDTO updateVendor(String vendorId, UpdateVendorRequest request) throws Exception {
        Vendor existingVendor = vendorRepository.findById(Integer.parseInt(vendorId))
                                             .orElseThrow(()->new NotFoundException("Invalid vendor id"));
        if(request.getName() != null) {
            existingVendor.setName((request.getName()));
        }
        if(request.getAddress() != null) {
            existingVendor.setAddress(request.getAddress());
        }
        if(request.getPhoneNumber() != null) {
            existingVendor.setPhoneNumber(request.getPhoneNumber());
        }
        if(request.getShopId() > 0){
            Shop shop = shopRepository.findById(request.getShopId())
                                  .orElseThrow(()-> new Exception("Shop was not found with id "+request.getShopId()));
            existingVendor.setShop(shop);
        }
        if(request.getCnic() != null){
            existingVendor.setCnic(request.getCnic());
        }
        existingVendor.setDue(request.getDue());

        Vendor updateVendor = vendorRepository.save(existingVendor);

        return VendorDTO.fromEntity(updateVendor);
    }

    public GetVendorResponse getVendorById(String id) throws Exception{
        Vendor vendor = vendorRepository.findById(Integer.parseInt(id)).orElseThrow(()-> new Exception("Vendor was not found with id "+id));
       GetVendorResponse getVendor = new GetVendorResponse(vendor.getId(), vendor.getDue(),vendor.getAddress(),vendor.getCnic(),vendor.getPhoneNumber(),vendor.getName(),vendor.getShop().getId());
       return getVendor;
    }

    public List<GetVendorResponse> findAllVendor(){
        List<Vendor> vendors = (List<Vendor>) vendorRepository.findAll();
        List<GetVendorResponse> allVendors = new ArrayList<>();
        for(Vendor vendor: vendors){
            GetVendorResponse getVendorsResponse = new GetVendorResponse(vendor.getId(), vendor.getShop().getId(),vendor.getName(), vendor.getAddress(), vendor.getCnic(), vendor.getPhoneNumber(), vendor.getDue());
            allVendors.add(getVendorsResponse);
        }
        return allVendors;
    }

    public void vendorDeleteById(String id) throws Exception{
        Vendor vendor = vendorRepository.findById(Integer.parseInt(id)).orElseThrow(()-> new Exception("Vendor was not found with id "+id));
        vendorRepository.deleteById(Integer.parseInt(id));
        throw new DeleteException("Vendor was sucessfully delete with id "+id);
    }
    
}
