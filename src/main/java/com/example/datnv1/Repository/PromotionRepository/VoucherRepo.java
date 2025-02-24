package com.example.datnv1.Repository.PromotionRepository;

import com.example.datnv1.Entity.Promotion.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepo extends JpaRepository<Voucher, Long> {
    boolean existsByCode(String code);
}
