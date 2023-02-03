package com.example.shop_accounts_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop_accounts_system.dto.AddCustomerDueRequest;
import com.example.shop_accounts_system.dto.CustomerDueDTO;
import com.example.shop_accounts_system.dto.GetCustomerDueResponse;
import com.example.shop_accounts_system.dto.UpdateCustomerDueRequest;
import com.example.shop_accounts_system.entity.Account;
import com.example.shop_accounts_system.entity.Customer;
import com.example.shop_accounts_system.entity.CustomerDue;
import com.example.shop_accounts_system.exception_handling.NotFoundException;
import com.example.shop_accounts_system.repository.AccountRepository;
import com.example.shop_accounts_system.repository.CustomerDueRepository;
import com.example.shop_accounts_system.repository.CustomerRespository;

@Service
public class CustomerDueService {
    
    @Autowired
    CustomerDueRepository customerDueRepository;

    @Autowired
    CustomerRespository customerRespository;

    @Autowired
    AccountRepository accountRepository;

    public CustomerDueDTO addCustomerDue(AddCustomerDueRequest addCustomerDueRequest)throws Exception{
        Customer customer = customerRespository.findById(addCustomerDueRequest.getCustomerId())
                            .orElseThrow(()-> new Exception("Customer was not found with the id"+addCustomerDueRequest.getCustomerId()));
        Account account = accountRepository.findById(addCustomerDueRequest.getAccountId())
                                           .orElseThrow(()-> new Exception("Account was not fount with id"+ addCustomerDueRequest.getAccountId()));
        int duePaidAmount = addCustomerDueRequest.getDueClearAmount();
        customer.setDues(customer.getDues()-duePaidAmount);
        customerRespository.save(customer);

        account.setBalance(account.getBalance()+duePaidAmount);
        accountRepository.save(account);

        CustomerDue customerDue = CustomerDue.toEntity(addCustomerDueRequest, customer, account);
        CustomerDue newCustomerDue = customerDueRepository.save(customerDue);
        return CustomerDueDTO.fromEntity(newCustomerDue);
    }

    public CustomerDueDTO updateCustomerDue(String cusomerDueId, UpdateCustomerDueRequest request) throws Exception {
        CustomerDue existingCustomerDue = customerDueRepository.findById(Integer.parseInt(cusomerDueId))
                                        .orElseThrow(()->new NotFoundException("Invalid customer due id"));
        Account account = accountRepository.findById(request.getAccountId())
                                        .orElseThrow(()-> new Exception("Account was not fount with id"+ request.getAccountId())); 
        account.setBalance(account.getBalance()+request.getDueClearAmount());
        accountRepository.save(account);                                
                                        
        if(request.getDueClearAmount() > 0) {
            existingCustomerDue.setDueClearAmount(request.getDueClearAmount());
        }
        if(request.getDate() != null) {
            existingCustomerDue.setDate(request.getDate());
        }

        CustomerDue updatedCustomerDue = customerDueRepository.save(existingCustomerDue);

        return CustomerDueDTO.fromEntity(updatedCustomerDue);
    }


    public GetCustomerDueResponse getCustomerDueById(String id) throws Exception{
        CustomerDue customerDue = customerDueRepository.findById(Integer.parseInt(id)).orElseThrow(()-> new Exception("Customer due was not found with id "+id));
       GetCustomerDueResponse getCustomerDue = new GetCustomerDueResponse(customerDue.getId(),customerDue.getCustomer().getId(),customerDue.getDueClearAmount(),customerDue.getDate(),customerDue.getAccount().getId());
       return getCustomerDue;
    }

    public List<GetCustomerDueResponse> findAllCustomerDue(){
        List<CustomerDue> customerDues = (List<CustomerDue>) customerDueRepository.findAll();
        List<GetCustomerDueResponse> allCustomerDues = new ArrayList<>();
        for(CustomerDue customerDue: customerDues){
            GetCustomerDueResponse getCustomerDueResponse = new GetCustomerDueResponse(customerDue.getId(),customerDue.getCustomer().getId(),customerDue.getDueClearAmount(),customerDue.getDate(),customerDue.getAccount().getId());
            allCustomerDues.add(getCustomerDueResponse);
        }
        return allCustomerDues;
    }
}
