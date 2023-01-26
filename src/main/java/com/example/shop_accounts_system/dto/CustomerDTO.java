package com.example.shop_accounts_system.dto;

import com.example.shop_accounts_system.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDTO {
    private int id;
    private int shopId;
    private String name;
    private String address;
    private String cnic;
    private String phoneNumber;
    private String dues;
    

    public static CustomerDTO fromEntity(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setShopId(customer.getShop().getId());
        customerDTO.setName(customer.getName());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setCnic(customer.getCnic());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setDues(customer.getDues());
        return customerDTO;

    }
}
















// package com.example.shop_accounts_system.dto;

// import com.example.shop_accounts_system.entity.Customer;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import lombok.ToString;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// @ToString
// public class CustomerDTO {
//     private int id;
//     private int shopId;
//     private String name;
//     private String address;
//     private String cnic;
//     private String phoneNumber;
//     private String dues;

//     public static CustomerDTO fromEntity(Customer customer){
//         CustomerDTO customerDTO = new CustomerDTO();
//         customerDTO.setId(customer.getId());
//         customerDTO.setName(customer.getName());
//         customerDTO.setAddress(customer.getAddress());
//         customerDTO.setCnic(customer.getCnic());
//         customerDTO.setPhoneNumber(customer.getPhoneNumber());
//         customerDTO.setDues(customer.getDues());
//         return customerDTO;

//     }
// }
