package com.example.shop_accounts_system.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.shop_accounts_system.dto.AddVendorDueRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VendorDue {
    @Id
    @GeneratedValue(generator = "increment")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    private Vendor vendor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    private int dueClearAmount;
    private Date date;

    public static VendorDue toEntity(AddVendorDueRequest addVendorDueRequest, Vendor vendor, Account account) throws Exception{
        VendorDue vendorDue = new VendorDue();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String date = addVendorDueRequest.getDate();
            vendorDue.setDate(simpleDateFormat.parse(date));
        } catch (Exception e) {
            throw new Exception("Date formate is invaild please use this formate dd-MM-yyyy");
        }
        vendorDue.setAccount(account);
        vendorDue.setDueClearAmount(addVendorDueRequest.getDueClearAmount());
        vendorDue.setVendor(vendor);
        return vendorDue;
    }
}
