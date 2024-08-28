package com.unibuc.event.ticketing.service;

import com.unibuc.event.ticketing.exception.AccountNotFoundException;
import com.unibuc.event.ticketing.exception.UserNotFoundException;
import com.unibuc.event.ticketing.model.Account;
import com.unibuc.event.ticketing.model.User;
import com.unibuc.event.ticketing.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    public Account getAccount(String accountId) throws AccountNotFoundException {
        var account = accountRepository.findById(accountId);
        if (account.isPresent()) return account.get();
        else throw new AccountNotFoundException();
    }
}
