package com.example.shop_accounts_system.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop_accounts_system.dto.AddCustomerRequest;
import com.example.shop_accounts_system.dto.CustomerDTO;
import com.example.shop_accounts_system.entity.Customer;
import com.example.shop_accounts_system.entity.Shop;
import com.example.shop_accounts_system.repository.CustomerRespository;
import com.example.shop_accounts_system.repository.ShopRepository;

@Service
public class CustomerService {
    
    @Autowired
    ShopRepository shopRepository;

    @Autowired
    CustomerRespository customerRespository;

    public CustomerDTO addCustomer(AddCustomerRequest addCustomerRequest) throws Exception{
        Optional<Shop> optionalShop = shopRepository.findById(addCustomerRequest.getShopId());
        if(optionalShop.isPresent()){
            Customer customer = Customer.toEntity(addCustomerRequest,optionalShop.get());
            Customer newCustomer = customerRespository.save(customer);
            return CustomerDTO.fromEntity(newCustomer);
        }
        else{
            throw new Exception("Shop was not found with id "+addCustomerRequest.getShopId());
        }
        
    }

}















// package com.example.shop_accounts_system.service;

// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.shop_accounts_system.dto.AddCustomerRequest;
// import com.example.shop_accounts_system.dto.CustomerDTO;
// import com.example.shop_accounts_system.entity.Customer;
// import com.example.shop_accounts_system.entity.Shop;
// import com.example.shop_accounts_system.repository.CustomerRespository;
// import com.example.shop_accounts_system.repository.ShopRepository;

// @Service
// public class CustomerService {
    
//     @Autowired
//     ShopRepository shopRepository;

//     @Autowired
//     CustomerRespository customerRespository;

//     public CustomerDTO addCustomer(AddCustomerRequest addCustomerRequest) throws Exception{
//         Optional<Shop> shop = shopRepository.findById(addCustomerRequest.getShopId());
//         if(shop.isPresent()){
//             Customer shopId = new Customer();
//             shopId.setShop(shop.get());
//             Customer customer = Customer.toEntity(addCustomerRequest);
//             Customer newCustomer = customerRespository.save(customer);
//             return CustomerDTO.fromEntity(newCustomer);
//         }
//         else{
//             throw new Exception("Shop was not found with id "+addCustomerRequest.getShopId());
//         }
        
//     }

// }
