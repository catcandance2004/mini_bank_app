package com.example.bankingapp.repository;

import com.example.bankingapp.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>
    //Extends JpaRepository for basic CRUD operations
{
    Optional<Account> findByEmail(String email);
    boolean existsByEmail(String email);
}
/*

 */