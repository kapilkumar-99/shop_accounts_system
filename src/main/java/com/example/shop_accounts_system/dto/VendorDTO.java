package com.example.shop_accounts_system.dto;


import com.example.shop_accounts_system.entity.Vendor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VendorDTO {
    private int id;
    private int shopId;
    private String name;
    private String address;
    private String cnic;
    private String phoneNumber;
    private String due;

    public static VendorDTO fromEntity(Vendor vendor){
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(vendor.getId());
        vendorDTO.setShopId(vendor.getShop().getId());
        vendorDTO.setName(vendor.getName());
        vendorDTO.setAddress(vendor.getAddress());
        vendorDTO.setCnic(vendor.getCnic());
        vendorDTO.setPhoneNumber(vendor.getPhoneNumber());
        vendorDTO.setDue(vendor.getDue());
        return vendorDTO;
    }
}
