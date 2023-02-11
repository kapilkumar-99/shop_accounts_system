package com.example.shop_accounts_system.service;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shop_accounts_system.dto.AccountDTO;
import com.example.shop_accounts_system.dto.AddAcountRequest;
import com.example.shop_accounts_system.dto.GetAccountResponse;
import com.example.shop_accounts_system.dto.UpdateAccountRequest;
import com.example.shop_accounts_system.entity.Account;
import com.example.shop_accounts_system.exception_handling.NotFoundException;
import com.example.shop_accounts_system.exception_handling.DeleteException;
import com.example.shop_accounts_system.repository.AccountRepository;

@Service
public class AccountService {
    
    @Autowired
    AccountRepository accountRepository;

    public AccountDTO addAccount(AddAcountRequest addAcountRequest){
        Account account = Account.toEntity(addAcountRequest);
        Account newAccount = accountRepository.save(account);
        return AccountDTO.fromEntity(newAccount);
    }

    public AccountDTO updateAccount(String accountId, UpdateAccountRequest request) throws Exception {
        Account existingAccount = accountRepository.findById(Integer.parseInt(accountId))
                                                   .orElseThrow(()->new NotFoundException("Invalid shop id "+accountId));
        if(request.getBankName() != null) {
            existingAccount.setBankName(request.getBankName());
        }
        if(request.getBalance() > 0) {
            existingAccount.setBalance(request.getBalance());
        }
        Account updatedAccount = accountRepository.save(existingAccount);

        return AccountDTO.fromEntity(updatedAccount);
    }

    public GetAccountResponse getAccountById(String id) throws Exception{
        Account account = accountRepository.findById(Integer.parseInt(id)).orElseThrow(()-> new NotFoundException("Account was not found with id "+id));
       GetAccountResponse getAccount = new GetAccountResponse(account.getId(), account.getBankName(), account.getBalance());
       return getAccount;
    }

    public List<GetAccountResponse> findAllAccount(){
        List<Account> accounts = (List<Account>) accountRepository.findAll();
        List<GetAccountResponse> allAccounts = new ArrayList<>();
        for(Account account: accounts){
            GetAccountResponse getAccountResponse = new GetAccountResponse(account.getId(), account.getBankName(),account.getBalance());
            allAccounts.add(getAccountResponse);
        }
        return allAccounts;
    }

    public void accountDeleteById(String id) throws Exception{
        Account account = accountRepository.findById(Integer.parseInt(id)).orElseThrow(()-> new Exception("Account was not found with id "+id));
        accountRepository.deleteById(Integer.parseInt(id));
        throw new DeleteException("Account was sucessfully delete with id "+id);
    }
}
