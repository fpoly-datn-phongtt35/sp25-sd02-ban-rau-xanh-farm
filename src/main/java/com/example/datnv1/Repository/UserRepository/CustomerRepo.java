package com.example.datnv1.Repository.UserRepository;

import com.example.datnv1.Entity.Account.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    long countByIdIn(List<Long> ids);
}
