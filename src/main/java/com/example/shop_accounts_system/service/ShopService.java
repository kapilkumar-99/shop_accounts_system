package com.example.shop_accounts_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shop_accounts_system.dto.AddShopRequest;
import com.example.shop_accounts_system.dto.GetShopResponse;
import com.example.shop_accounts_system.dto.ShopDTO;
import com.example.shop_accounts_system.dto.UpdateShopRequest;
import com.example.shop_accounts_system.entity.Shop;
import com.example.shop_accounts_system.exception_handling.NotFoundException;
import com.example.shop_accounts_system.exception_handling.DeleteException;
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

    public GetShopResponse getShopById(String id) throws Exception{
        Shop shop = shopRepository.findById(Integer.parseInt(id)).orElseThrow(()-> new Exception("Shop was not found with id "+id));
       GetShopResponse getShop = new GetShopResponse(shop.getId(), shop.getAddress(), shop.getName(), shop.getPhoneNumber());
       return getShop;
    }

    public List<GetShopResponse> findAllShop(){
        List<Shop> shops = (List<Shop>) shopRepository.findAll();
        List<GetShopResponse> allShops = new ArrayList<>();
        for(Shop shop: shops){
            GetShopResponse getShopsResponse = new GetShopResponse(shop.getId(), shop.getName(), shop.getAddress(), shop.getPhoneNumber());
            allShops.add(getShopsResponse);
        }
        return allShops;
    }

    public void shopDeleteById(String id) throws Exception{
        Shop shop = shopRepository.findById(Integer.parseInt(id)).orElseThrow(()-> new Exception("Shop was not found with id "+id));
        shopRepository.deleteById(Integer.parseInt(id));
        throw new DeleteException("Shop was sucessfully delete with id "+id);
    }
}
