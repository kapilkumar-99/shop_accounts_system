package com.example.shop_accounts_system.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.shop_accounts_system.dto.AddVendorRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Vendor {
    @Id
    @GeneratedValue(generator = "increment")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;

    // private String shopId;
    private String name;
    private String address;
    private String cnic;
    private String phoneNumber;
    private String due;

    @OneToMany (mappedBy = "vendor")
    private List<Purchase> purchase;


    public static Vendor toEntity(AddVendorRequest addVendorRequest, Shop shop){
        Vendor vendor = new Vendor();
        vendor.setName(addVendorRequest.getName());
        vendor.setShop(shop);
        vendor.setAddress(addVendorRequest.getAddress());
        vendor.setCnic(addVendorRequest.getCnic());
        vendor.setPhoneNumber(addVendorRequest.getPhoneNumber());
        vendor.setDue(addVendorRequest.getDue());
        return vendor;
    }
   
}
