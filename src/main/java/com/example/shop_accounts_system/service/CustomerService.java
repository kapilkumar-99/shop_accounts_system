package com.example.shop_accounts_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shop_accounts_system.dto.AddCustomerRequest;
import com.example.shop_accounts_system.dto.CustomerDTO;
import com.example.shop_accounts_system.dto.GetCustomerResponse;
import com.example.shop_accounts_system.dto.UpdateCustomerRequest;
import com.example.shop_accounts_system.entity.Customer;
import com.example.shop_accounts_system.entity.Shop;
import com.example.shop_accounts_system.exception_handling.NotFoundException;
import com.example.shop_accounts_system.exception_handling.DeleteException;
import com.example.shop_accounts_system.repository.CustomerRespository;
import com.example.shop_accounts_system.repository.ShopRepository;

@Service
public class CustomerService {
    
    @Autowired
    ShopRepository shopRepository;

    @Autowired
    CustomerRespository customerRespository;

    public CustomerDTO addCustomer(AddCustomerRequest addCustomerRequest) throws Exception{
        Shop shop = shopRepository.findById(addCustomerRequest.getShopId())
                                  .orElseThrow(()-> new Exception("Shop was not found with id "+ addCustomerRequest.getShopId()));
        Customer customer = Customer.toEntity(addCustomerRequest,shop);
        Customer newCustomer = customerRespository.save(customer);
        return CustomerDTO.fromEntity(newCustomer);        
    }


    public CustomerDTO updateCustomer(String customerId, UpdateCustomerRequest request) throws Exception {
        Customer existingCustomer = customerRespository.findById(Integer.parseInt(customerId))
                                                       .orElseThrow(()->new NotFoundException("Invalid customer id "+customerId));
        if(request.getName() != null) {
           existingCustomer.setName(request.getName());
        }
        if(request.getAddress() != null) {
            existingCustomer.setAddress(request.getAddress());
        }
        if(request.getPhoneNumber() != null) {
            existingCustomer.setPhoneNumber(request.getPhoneNumber());
        }
        if(request.getShopId() > 0){
            Shop shop = shopRepository.findById(request.getShopId())
                                  .orElseThrow(()-> new Exception("Shop was not found with id "+request.getShopId()));
            existingCustomer.setShop(shop);
        }
        if(request.getCnic() != null){
            existingCustomer.setCnic(request.getCnic());
        }
        existingCustomer.setDues(request.getDues());

        Customer updateCustomer = customerRespository.save(existingCustomer);

        return CustomerDTO.fromEntity(updateCustomer);
    }

    public GetCustomerResponse getCustomerById(String id) throws Exception{
       Customer customer = customerRespository.findById(Integer.parseInt(id)).orElseThrow(()-> new Exception("Customer was not found with id "+id));
       GetCustomerResponse getCustomer = new GetCustomerResponse(customer.getId(), customer.getDues(),customer.getAddress(),customer.getCnic(),customer.getPhoneNumber(),customer.getName(),customer.getShop().getId());
       return getCustomer;
    }

    public List<GetCustomerResponse> findAllCustomer(){
        List<Customer> customers = (List<Customer>) customerRespository.findAll();
        List<GetCustomerResponse> allCustomers = new ArrayList<>();
        for(Customer customer: customers){
            GetCustomerResponse getCustomerResponse = new GetCustomerResponse(customer.getId(), customer.getShop().getId(),customer.getName(), customer.getAddress(), customer.getCnic(), customer.getPhoneNumber(), customer.getDues());
            allCustomers.add(getCustomerResponse);
        }
        return allCustomers;
    }

    public void customerDeleteById(String id) throws Exception{
        Customer customer = customerRespository.findById(Integer.parseInt(id)).orElseThrow(()-> new Exception("Customer was not found with id "+id));
        customerRespository.deleteById(Integer.parseInt(id));
        throw new DeleteException("Customer was sucessfully delete with id "+id);
        // return "deleted sucessfully with id "+id;
    }


}
