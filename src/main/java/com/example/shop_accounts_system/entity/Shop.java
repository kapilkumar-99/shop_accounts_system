package com.example.shop_accounts_system.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.shop_accounts_system.dto.AddShopRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Shop {
    @Id
    @GeneratedValue(generator = "increment")
    private int id;
    private String name;
    private String address;
    private String phoneNumber;

    @OneToMany (mappedBy = "shop")
    private List<Vendor> vendor;

    @OneToMany (mappedBy = "shop")
    private List<Customer> customer;

    @OneToMany (mappedBy = "shop")
    private List<Purchase> purchase;

    public static Shop toEntity(AddShopRequest addShopRequest){
        Shop shop = new Shop();
        shop.setName(addShopRequest.getName());
        shop.setAddress(addShopRequest.getAddress());
        shop.setPhoneNumber(addShopRequest.getPhoneNumber());
        return shop;
    }
}
