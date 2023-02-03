package com.example.shop_accounts_system.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
    
    @Temporal(TemporalType.DATE)
    private Date dateOfClearing;
    private int itemQuantity;
    private int dueAmount;
    private int totalAmount;

    public static Purchase toEntity(AddPurchaseRequest addPurchaseRequest,Shop shop, Vendor vendor, Product product,Account account)throws Exception{
        Purchase purchase = new Purchase();
    
        if(addPurchaseRequest.getDateOfClearing() == null && addPurchaseRequest.getDueAmount() == 0){
                throw new Exception("Please add clearance date as no due remains");
            }
        
        if(addPurchaseRequest.getDateOfClearing() != null && addPurchaseRequest.getDueAmount() > 0){
            throw new Exception("Clearance date can not use as some due remaining");
        }
        purchase.setShop(shop);
        purchase.setVendor(vendor);
        purchase.setProduct(product);
        purchase.setAccount(account);
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String date = addPurchaseRequest.getDateOfClearing();
            purchase.setDateOfClearing(simpleDateFormat.parse(date));
        } catch (Exception e) {
            throw new Exception("Date formate is invaild please use this formate dd-MM-yyyy");
        }
        // purchase.setDateOfClearing(addPurchaseRequest.getDateOfClearing());
        purchase.setItemQuantity(addPurchaseRequest.getItemQuantity());
        purchase.setDueAmount(addPurchaseRequest.getDueAmount());
        purchase.setTotalAmount(addPurchaseRequest.getTotalAmount());
        return purchase;
    }

}
