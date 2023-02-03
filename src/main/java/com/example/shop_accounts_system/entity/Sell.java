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

import com.example.shop_accounts_system.dto.AddSellRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sell {
    @Id
    @GeneratedValue(generator = "increment")
    private int id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    
    @Temporal(TemporalType.DATE)
    private Date dateOfClearance;
    private int sellPrice;
    private int quantity;
    private int due;
    private int totalAmount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public static Sell toEntity(AddSellRequest addSellRequest, Shop shop, Customer customer, Product product, Account account) throws Exception{
        Sell sell = new Sell();
        sell.setShop(shop);
        sell.setCustomer(customer);
        sell.setProduct(product);
        sell.setAccount(account);
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String date = addSellRequest.getDateOfClearance();
            sell.setDateOfClearance(simpleDateFormat.parse(date));
        } catch (Exception e) {
            throw new Exception("Date formate is invaild please use this formate dd-MM-yyyy");
        }
        // sell.setDateOfClearance(addSellRequest.getDateOfClearance());
        sell.setSellPrice(addSellRequest.getSellPrice());
        sell.setQuantity(addSellRequest.getQuantity());
        sell.setDue(addSellRequest.getDue());
        sell.setTotalAmount(addSellRequest.getTotalAmount());
        return sell;
    }
}
