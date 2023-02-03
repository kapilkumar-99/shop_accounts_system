package com.example.shop_accounts_system.dto;

import java.util.Date;

import com.example.shop_accounts_system.entity.VendorDue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDueDTO {
    private int id;
    private int vendorId;
    private int dueClearAmount;
    private Date date;

    public static VendorDueDTO fromEntity(VendorDue vendorDue){
        VendorDueDTO vendorDueDTO = new VendorDueDTO();
        vendorDueDTO.setId(vendorDue.getId());
        vendorDueDTO.setDate(vendorDue.getDate());
        vendorDueDTO.setDueClearAmount(vendorDue.getDueClearAmount());
        vendorDueDTO.setVendorId(vendorDue.getVendor().getId());
        return vendorDueDTO;
    }
}
