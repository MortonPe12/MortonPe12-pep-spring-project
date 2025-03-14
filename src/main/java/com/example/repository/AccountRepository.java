package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    boolean existsByUsername(String username);

    boolean existsByAccountId(Integer accountId);

    Account findByUsernameAndPassword(String username, String password);

    Account findByAccountId(Integer accountId);

}