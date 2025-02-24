package com.example.datnv1.Service.PromotionService;

import com.example.datnv1.DTO.Convert.VoucherMapper;
import com.example.datnv1.DTO.Req.Promotion.VoucherReqDTO;
import com.example.datnv1.Entity.Promotion.Voucher;
import com.example.datnv1.Repository.PromotionRepository.VoucherRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VoucherService {
    public VoucherRepo voucherRepo;
    public VoucherMapper voucherMapper;

    public Voucher addVoucher(VoucherReqDTO voucherReqDTO) {
        // Kiểm tra xem code đã tồn tại chưa
        if (voucherRepo.existsByCode(voucherReqDTO.getCode())) {
            throw new IllegalArgumentException("Mã voucher '" + voucherReqDTO.getCode() + "' đã tồn tại.");
        }

        // Chuyển đổi DTO thành entity
        Voucher voucher = voucherMapper.toEntity(voucherReqDTO);

        // Lưu vào database
        return voucherRepo.save(voucher);
    }
}
