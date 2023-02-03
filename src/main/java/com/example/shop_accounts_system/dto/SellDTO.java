package com.example.shop_accounts_system.dto;

import java.util.Date;

import com.example.shop_accounts_system.entity.Sell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellDTO {
    private int id;
    private int shopId;
    private int customerId;
    private int productId;
    private int accountId;
    private Date dateOfClearance;
    private int sellPrice;
    private int quantity;
    private int due;
    private int totalAmount;

    public static SellDTO fromEntity(Sell sell){
        SellDTO sellDTO = new SellDTO();
        sellDTO.setId(sell.getId());
        sellDTO.setCustomerId(sell.getCustomer().getId());
        sellDTO.setShopId(sell.getShop().getId());
        sellDTO.setProductId(sell.getProduct().getId());
        sellDTO.setAccountId(sell.getAccount().getId());
        sellDTO.setDateOfClearance(sell.getDateOfClearance());
        sellDTO.setSellPrice(sell.getSellPrice());
        sellDTO.setQuantity(sell.getQuantity());
        sellDTO.setDue(sell.getDue());
        sellDTO.setTotalAmount(sell.getTotalAmount());
        return sellDTO;
    }
}
