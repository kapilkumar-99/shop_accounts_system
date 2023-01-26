package com.example.shop_accounts_system.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.shop_accounts_system.dto.AddPurchaseRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Purchase {
    @Id
    @GeneratedValue(generator = "increment")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_Id", referencedColumnName = "id")
    private Shop shop;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendor_Id", referencedColumnName = "id")
    private Vendor vendor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_Id", referencedColumnName = "id")
    private Product product;
    
    private String dateOfClearing;
    private int accountId;
    private int itemQuantity;
    private int dueAmount;
    private int totalAmount;

    public static Purchase toEntity(AddPurchaseRequest addPurchaseRequest,Shop shop, Vendor vendor, Product product){
        Purchase purchase = new Purchase();
        purchase.setShop(shop);
        purchase.setVendor(vendor);
        purchase.setProduct(product);
        purchase.setDateOfClearing(addPurchaseRequest.getDateOfClearing());
        purchase.setAccountId(addPurchaseRequest.getAccountId());
        purchase.setItemQuantity(addPurchaseRequest.getItemQuantity());
        purchase.setDueAmount(addPurchaseRequest.getDueAmount());
        purchase.setTotalAmount(addPurchaseRequest.getTotalAmount());
        return purchase;
    }

}
