package com.example.datnv1.DTO.Convert;

import com.example.datnv1.DTO.Req.Promotion.VoucherReqDTO;
import com.example.datnv1.Entity.Account.Customer;
import com.example.datnv1.Entity.Product.Product;
import com.example.datnv1.Entity.Product.ProductDetail;
import com.example.datnv1.Entity.Promotion.Voucher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VoucherMapper {
    public Voucher toEntity(VoucherReqDTO dto) {
        Voucher voucher = new Voucher();
        voucher.setName(dto.getName());
        voucher.setCode(dto.getCode());
        voucher.setQuantity(dto.getQuantity());
        voucher.setMaxDiscount(dto.getMaxDiscount());
        voucher.setMinBill(dto.getMinBill());
        voucher.setValue(dto.getValue());
        voucher.setVoucherType(dto.getVoucherType());
        voucher.setDiscountType(dto.getDiscountType());
        voucher.setStartAt(dto.getStartAt());
        voucher.setEndAt(dto.getEndAt());
        voucher.setStatus(dto.getStatus());

        // Ánh xạ danh sách ID sang danh sách thực thể nếu cần
        if (dto.getApplicableProductIds() != null) {
            List<Product> products = dto.getApplicableProductIds().stream()
                    .map(id -> {
                        Product product = new Product();
                        product.setId(id);
                        return product;
                    })
                    .collect(Collectors.toList());
            voucher.setApplicableProducts(products);
        }

        if (dto.getApplicableCustomerIds() != null) {
            List<Customer> customers = dto.getApplicableCustomerIds().stream()
                    .map(id -> {
                        Customer customer = new Customer();
                        customer.setId(id);
                        return customer;
                    })
                    .collect(Collectors.toList());
            voucher.setApplicableCustomers(customers);
        }

        if (dto.getApplicableProductDetailIds() != null) {
            List<ProductDetail> productDetails = dto.getApplicableProductDetailIds().stream()
                    .map(id -> {
                        ProductDetail productDetail = new ProductDetail();
                        productDetail.setId(id);
                        return productDetail;
                    })
                    .collect(Collectors.toList());
            voucher.setApplicableProductDetails(productDetails);
        }

        return voucher;
    }
}
