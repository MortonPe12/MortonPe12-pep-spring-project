package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<Account> registerAccount(Account account) {
        if (account.getUsername().isBlank() || account.getPassword().length() < 4) {
            return ResponseEntity.status(400).body(null);
        }
    
        if (accountRepository.existsByUsername(account.getUsername()) == true) {
            return ResponseEntity.status(409).body(null);
        }
    
        Account savedAccount = accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.OK).body(savedAccount);
    }

    public ResponseEntity<Account> login(Account account) {
        String username = account.getUsername();
        String password = account.getPassword();

        Account authenicate = accountRepository.findByUsernameAndPassword(username, password);
        if (authenicate == null) {
            return ResponseEntity.status(401).body(null);
        }

        return ResponseEntity.ok(authenicate);
    }

}
