package com.srv.springbootBankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srv.springbootBankingapp.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
