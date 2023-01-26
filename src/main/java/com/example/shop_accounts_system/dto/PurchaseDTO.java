package com.example.shop_accounts_system.dto;

import com.example.shop_accounts_system.entity.Purchase;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO {
    private int shopId;
    private int vendorId;
    private int productId;
    private String dateOfClearing;
    private int accountId;
    private int itemQuantity;
    private int dueAmount;
    private int totalAmount;

    public static PurchaseDTO fromEntity(Purchase purchase){
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setShopId(purchase.getShop().getId());
        purchaseDTO.setProductId(purchase.getProduct().getId());
        purchaseDTO.setVendorId(purchase.getVendor().getId());
        purchaseDTO.setDateOfClearing(purchase.getDateOfClearing());
        purchaseDTO.setAccountId(purchase.getAccountId());
        purchaseDTO.setItemQuantity(purchase.getItemQuantity());
        purchaseDTO.setDueAmount(purchase.getDueAmount());
        purchaseDTO.setTotalAmount(purchase.getTotalAmount());
        return purchaseDTO;
    }
}
