package com.example.shop_accounts_system.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.shop_accounts_system.dto.AddCustomerRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(generator = "increment")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_Id", referencedColumnName = "id")
    private Shop shop;

    private String name;
    private String address;
    private String cnic;
    private String phoneNumber;
    private String dues;

    public static Customer toEntity(AddCustomerRequest addCustomerRequest, Shop shop){
        Customer customer = new Customer();
        customer.setName(addCustomerRequest.getName());
        customer.setShop(shop);
        customer.setAddress(addCustomerRequest.getAddress());
        customer.setCnic(addCustomerRequest.getCnic());
        customer.setPhoneNumber(addCustomerRequest.getPhoneNumber());
        customer.setDues(addCustomerRequest.getDues());
        return customer;
    }
}













// package com.example.shop_accounts_system.entity;

// import javax.persistence.CascadeType;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;

// import com.example.shop_accounts_system.dto.AddCustomerRequest;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// @Entity
// public class Customer {
//     @Id
//     @GeneratedValue(generator = "increment")
//     private int id;

//     @ManyToOne(cascade = CascadeType.ALL)
//     @JoinColumn(name = "shop_id", referencedColumnName = "id")
//     private Shop shop;

//     private String name;
//     private String address;
//     private String cnic;
//     private String phoneNumber;
//     private String dues;

//     public static Customer toEntity(AddCustomerRequest addCustomerRequest){
//         Customer customer = new Customer();
//         customer.setName(addCustomerRequest.getName());
//         customer.setAddress(addCustomerRequest.getAddress());
//         customer.setCnic(addCustomerRequest.getCnic());
//         customer.setPhoneNumber(addCustomerRequest.getPhoneNumber());
//         customer.setDues(addCustomerRequest.getDues());
//         return customer;
//     }
// }
