package com.challenge.question5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.question5.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

}
