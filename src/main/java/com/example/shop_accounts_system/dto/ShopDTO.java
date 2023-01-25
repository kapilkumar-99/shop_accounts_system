package com.example.shop_accounts_system.dto;

import com.example.shop_accounts_system.entity.Shop;

import lombok.Data;

@Data
public class ShopDTO {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;

    public static ShopDTO fromEntity(Shop shop){
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setId(shop.getId());
        shopDTO.setName(shop.getName());
        shopDTO.setAddress(shop.getAddress());
        shopDTO.setPhoneNumber(shop.getPhoneNumber());
        return shopDTO;
    }
}
