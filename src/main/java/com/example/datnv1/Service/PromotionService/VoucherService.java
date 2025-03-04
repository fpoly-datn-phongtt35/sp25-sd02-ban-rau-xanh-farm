package com.example.datnv1.Service.PromotionService;

import com.example.datnv1.DTO.Convert.VoucherMapper;
import com.example.datnv1.DTO.Req.Promotion.VoucherReqDTO;
import com.example.datnv1.Entity.Account.Customer;
import com.example.datnv1.Entity.Product.Product;
import com.example.datnv1.Entity.Product.ProductDetail;
import com.example.datnv1.Entity.Promotion.Voucher;
import com.example.datnv1.Repository.ProductRepository.ProductDetailRepo;
import com.example.datnv1.Repository.ProductRepository.ProductRepo;
import com.example.datnv1.Repository.PromotionRepository.VoucherRepo;
import com.example.datnv1.Repository.UserRepository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VoucherService {
    public VoucherRepo voucherRepo;
    public VoucherMapper voucherMapper;
    private ProductRepo productRepo;
    private CustomerRepo customerRepo;
    private ProductDetailRepo productDetailRepo;

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

    public Voucher updateVoucher(Long id, VoucherReqDTO voucherReqDTO) {
        // Kiểm tra xem voucher có tồn tại không
        Voucher existingVoucher = voucherRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Voucher không tồn tại với id: " + id));

        // Kiểm tra nếu mã voucher đã tồn tại trong database (trừ voucher hiện tại)
        if (voucherRepo.existsByCodeAndIdNot(voucherReqDTO.getCode(), id)) {
            throw new IllegalArgumentException("Mã voucher đã tồn tại: " + voucherReqDTO.getCode());
        }

        // Cập nhật thông tin voucher từ DTO
        existingVoucher.setName(voucherReqDTO.getName());
        existingVoucher.setCode(voucherReqDTO.getCode());
        existingVoucher.setQuantity(voucherReqDTO.getQuantity());
        existingVoucher.setMaxDiscount(voucherReqDTO.getMaxDiscount());
        existingVoucher.setMinBill(voucherReqDTO.getMinBill());
        existingVoucher.setValue(voucherReqDTO.getValue());
        existingVoucher.setVoucherType(voucherReqDTO.getVoucherType());
        existingVoucher.setDiscountType(voucherReqDTO.getDiscountType());
        existingVoucher.setStartAt(voucherReqDTO.getStartAt());
        existingVoucher.setEndAt(voucherReqDTO.getEndAt());
        existingVoucher.setStatus(voucherReqDTO.getStatus());

        // Cập nhật danh sách sản phẩm áp dụng
        if (voucherReqDTO.getApplicableProductIds() != null) {
            List<Product> products = productRepo.findAllById(voucherReqDTO.getApplicableProductIds());
            existingVoucher.setApplicableProducts(products);
        }

        // Cập nhật danh sách khách hàng áp dụng
        if (voucherReqDTO.getApplicableCustomerIds() != null) {
            List<Customer> customers = customerRepo.findAllById(voucherReqDTO.getApplicableCustomerIds());
            existingVoucher.setApplicableCustomers(customers);
        }

        // Cập nhật danh sách SKU áp dụng
        if (voucherReqDTO.getApplicableProductDetailIds() != null) {
            List<ProductDetail> productDetails = (List<ProductDetail>) productDetailRepo.findAllById(voucherReqDTO.getApplicableProductDetailIds());
            existingVoucher.setApplicableProductDetails(productDetails);
        }

        // Lưu voucher đã cập nhật vào database
        return voucherRepo.save(existingVoucher);
    }

    public List<Voucher> getAllVouchers() {
        return voucherRepo.findAll();
    }
}
