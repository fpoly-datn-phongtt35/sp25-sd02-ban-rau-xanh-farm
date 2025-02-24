package com.example.datnv1.Repository.UserRepository;

import com.example.datnv1.Entity.Promotion.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Voucher, Long> {
    long countByIdIn(List<Long> ids);
}
