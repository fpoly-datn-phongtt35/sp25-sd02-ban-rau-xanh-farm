package com.example.datnv1.Rest.Promotion;

import com.example.datnv1.DTO.ApiResponse;
import com.example.datnv1.DTO.Req.Promotion.VoucherReqDTO;
import com.example.datnv1.Entity.Promotion.Voucher;
import com.example.datnv1.Service.PromotionService.VoucherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vouchers")
@RequiredArgsConstructor
public class VoucherRest {

    private final VoucherService voucherService;

    @PostMapping("/add")
    public ResponseEntity<?> addVoucher(@RequestBody @Valid VoucherReqDTO voucherReqDTO) {
        Voucher newVoucher = voucherService.addVoucher(voucherReqDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(newVoucher, "Thêm mới voucher thành công"));
    }
}
