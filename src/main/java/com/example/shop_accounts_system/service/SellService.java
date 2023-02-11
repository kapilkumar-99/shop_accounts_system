package com.example.shop_accounts_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shop_accounts_system.dto.AddSellRequest;
import com.example.shop_accounts_system.dto.GetSellResponse;
import com.example.shop_accounts_system.dto.SellDTO;
import com.example.shop_accounts_system.entity.Account;
import com.example.shop_accounts_system.entity.Customer;
import com.example.shop_accounts_system.entity.Product;
import com.example.shop_accounts_system.entity.Sell;
import com.example.shop_accounts_system.entity.Shop;
import com.example.shop_accounts_system.repository.AccountRepository;
import com.example.shop_accounts_system.repository.CustomerRespository;
import com.example.shop_accounts_system.repository.ProductRepository;
import com.example.shop_accounts_system.repository.SellRepository;
import com.example.shop_accounts_system.repository.ShopRepository;

@Service
public class SellService {
    
    @Autowired
    SellRepository sellRepository;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    CustomerRespository customerRespository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AccountRepository accountRepository;


    @Transactional
    public SellDTO addSell(AddSellRequest addSellRequest) throws Exception{
        Shop shop = shopRepository.findById(addSellRequest.getShopId())
                    .orElseThrow(()-> new Exception("No shop was found with shop id "+addSellRequest.getShopId())); 
        Customer customer = customerRespository.findById(addSellRequest.getCustomerId())
                    .orElseThrow(()-> new Exception("No customer was found with customer id "+addSellRequest.getCustomerId()));
        Product product = productRepository.findById(addSellRequest.getProductId())
                    .orElseThrow(()-> new Exception("No product wasa found with product id "+addSellRequest.getProductId()));
        Account account = accountRepository.findById(addSellRequest.getAccountId())
                    .orElseThrow(()-> new Exception("Account was not found with id "+addSellRequest.getAccountId()));
        int sellTotalAmount = addSellRequest.getTotalAmount();
        int sellDueAmount = addSellRequest.getDue();
        int sellAmount = sellTotalAmount-sellDueAmount;
        account.setBalance(account.getBalance()+sellAmount);
        accountRepository.save(account);
        Sell sell = Sell.toEntity(addSellRequest, shop, customer, product,account);
        Sell newSell = sellRepository.save(sell);
        return SellDTO.fromEntity(newSell);
    }

    public GetSellResponse getSellById(String id) throws Exception{
        Sell sell = sellRepository.findById(Integer.parseInt(id)).orElseThrow(()-> new Exception("Sell was not found with id "+id));
        GetSellResponse getSell = new GetSellResponse(sell.getId(),sell.getShop().getId(),sell.getCustomer().getId(),sell.getProduct().getId(), sell.getAccount().getId(), sell.getDateOfClearance(),sell.getSellPrice(), sell.getQuantity(),sell.getDue(),sell.getTotalAmount());
        return getSell;
    }

    public List<GetSellResponse> findAllSell(){
        List<Sell> sells = (List<Sell>) sellRepository.findAll();
        List<GetSellResponse> allSells = new ArrayList<>();
        for(Sell sell: sells){
            GetSellResponse getPurchaseResponse = new GetSellResponse(sell.getId(),sell.getShop().getId(),sell.getCustomer().getId(),sell.getProduct().getId(), sell.getAccount().getId(), sell.getDateOfClearance(),sell.getSellPrice(), sell.getQuantity(),sell.getDue(),sell.getTotalAmount());
            allSells.add(getPurchaseResponse);
        }
        return allSells;
    }
}
