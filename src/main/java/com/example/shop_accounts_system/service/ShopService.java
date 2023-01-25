package com.example.shop_accounts_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop_accounts_system.dto.AddShopRequest;
import com.example.shop_accounts_system.dto.ShopDTO;
import com.example.shop_accounts_system.dto.UpdateShopRequest;
import com.example.shop_accounts_system.entity.Shop;
import com.example.shop_accounts_system.exception_handling.NotFoundException;
import com.example.shop_accounts_system.repository.ShopRepository;

@Service
public class ShopService {
    
    @Autowired
    ShopRepository shopRepository;

    public ShopDTO addShop(AddShopRequest addShopRequest) throws Exception{
        String name = addShopRequest.getName();
        if(name.length() < 3){
            throw new Exception("Shop name should me larger than 3");
        }
        Shop shop = Shop.toEntity(addShopRequest);
        Shop newShop = shopRepository.save(shop);
        return ShopDTO.fromEntity(newShop); 
    }

    public ShopDTO updateShop(String shopId, UpdateShopRequest request) throws Exception {
        var existingShop = shopRepository.findById(Integer.parseInt(shopId))
                                        .orElseThrow(
                                            ()->new NotFoundException("Invalid shop id")
                                        );
        if(request.getName() != null) {
            existingShop.setName((request.getName()));
        }
        if(request.getAddress() != null) {
            existingShop.setAddress(request.getAddress());
        }
        if(request.getPhoneNumber() != null) {
            existingShop.setPhoneNumber(request.getPhoneNumber());
        }

        Shop updatedShop = shopRepository.save(existingShop);

        return ShopDTO.fromEntity(updatedShop);
    }
}
