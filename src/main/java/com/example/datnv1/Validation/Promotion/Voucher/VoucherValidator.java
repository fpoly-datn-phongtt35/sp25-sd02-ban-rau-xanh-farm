package com.example.datnv1.Validation.Promotion.Voucher;

import com.example.datnv1.DTO.Req.Promotion.VoucherReqDTO;
import com.example.datnv1.Enum.DiscountType;
import com.example.datnv1.Enum.VoucherType;
import com.example.datnv1.Repository.ProductRepository.ProductDetailRepo;
import com.example.datnv1.Repository.ProductRepository.ProductRepo;
import com.example.datnv1.Repository.UserRepository.CustomerRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
public class VoucherValidator implements ConstraintValidator<ValidVoucher, VoucherReqDTO> {

    private final ProductRepo productRepository;
    private final CustomerRepo customerRepository;
    private final ProductDetailRepo productDetailRepository;

    @Override
    public boolean isValid(VoucherReqDTO dto, ConstraintValidatorContext context) {
        boolean isValid = true;

        // Nếu discountType = PERCENTAGE, kiểm tra maxDiscount >= value * minBill / 100
        if (dto.getDiscountType() == DiscountType.PERCENTAGE) {
            double calculatedMaxDiscount = (dto.getValue() * dto.getMinBill()) / 100;
            if (dto.getMaxDiscount() != null && dto.getMaxDiscount() < calculatedMaxDiscount) {
                addViolation(context, "maxDiscount", "maxDiscount phải lớn hơn value * minBill / 100");
                isValid = false;
            }
        }

        // Nếu discountType = AMOUNT, kiểm tra value <= minBill
        if (dto.getDiscountType() == DiscountType.AMOUNT && dto.getValue() > dto.getMinBill()) {
            addViolation(context, "value", "Giá trị giảm không thể lớn hơn tổng đơn hàng");
            isValid = false;
        }

        // Kiểm tra startAt < endAt
        if (dto.getStartAt().isAfter(dto.getEndAt())) {
            addViolation(context, "endAt", "Thời gian kết thúc phải sau thời gian bắt đầu");
            isValid = false;
        }

        // Không được đặt endAt quá 3 tháng so với startAt
        if (ChronoUnit.MONTHS.between(dto.getStartAt(), dto.getEndAt()) > 3) {
            addViolation(context, "endAt", "Thời gian kết thúc không được quá 3 tháng sau thời gian bắt đầu");
            isValid = false;
        }

        // Kiểm tra điều kiện của Voucher sản phẩm (PRODUCT)
        if (dto.getVoucherType() == VoucherType.PRODUCT) {
            if (isListNullOrEmpty(dto.getApplicableProductIds()) && isListNullOrEmpty(dto.getApplicableProductDetailIds())) {
                addViolation(context, "applicableProductIds", "Voucher sản phẩm phải có ít nhất một danh sách sản phẩm hoặc sản phẩm chi tiết");
                addViolation(context, "applicableProductDetailIds", "Voucher sản phẩm phải có ít nhất một danh sách sản phẩm hoặc sản phẩm chi tiết");
                isValid = false;
            }

            isValid = isValidProductListAndProductDetailList(dto, context, isValid);
        }

        // Kiểm tra điều kiện của Voucher riêng tư (PRIVATE)
        if (dto.getVoucherType() == VoucherType.PRIVATE) {
            if (isListNullOrEmpty(dto.getApplicableCustomerIds())) {
                addViolation(context, "applicableCustomerIds", "Voucher riêng tư phải có danh sách khách hàng được chọn");
                isValid = false;
            }

            if (!isListNullOrEmpty(dto.getApplicableCustomerIds()) && !isValidCustomerList(dto.getApplicableCustomerIds())) {
                addViolation(context, "applicableCustomerIds", "Danh sách khách hàng chứa khách hàng không tồn tại");
                isValid = false;
            }

            // Kiểm tra điều kiện của Voucher riêng tư áp dụng cho từng sản phẩm cụ thể
            if (!dto.isApplyToAllProducts()) { // Nếu không áp dụng cho tất cả sản phẩm
                if (isListNullOrEmpty(dto.getApplicableProductIds()) && isListNullOrEmpty(dto.getApplicableProductDetailIds())) {
                    addViolation(context, "applicableProductIds", "Voucher riêng tư phải áp dụng cho ít nhất một sản phẩm hoặc sản phẩm chi tiết nếu không áp dụng cho toàn bộ sản phẩm");
                    addViolation(context, "applicableProductDetailIds", "Voucher riêng tư phải áp dụng cho ít nhất một sản phẩm hoặc sản phẩm chi tiết nếu không áp dụng cho toàn bộ sản phẩm");
                    isValid = false;
                }

                isValid = isValidProductListAndProductDetailList(dto, context, isValid);
            }
        }


        return isValid;
    }

    private boolean isValidProductListAndProductDetailList(VoucherReqDTO dto, ConstraintValidatorContext context, boolean isValid) {
        if (!isListNullOrEmpty(dto.getApplicableProductIds()) && !isValidProductList(dto.getApplicableProductIds())) {
            addViolation(context, "applicableProductIds", "Danh sách sản phẩm chứa sản phẩm không tồn tại");
            isValid = false;
        }

        if (!isListNullOrEmpty(dto.getApplicableProductDetailIds()) && !isValidProductDetailList(dto.getApplicableProductDetailIds())) {
            addViolation(context, "applicableProductDetailIds", "Danh sách SKU chứa SKU không tồn tại");
            isValid = false;
        }
        return isValid;
    }

    /**
     * Kiểm tra danh sách sản phẩm có hợp lệ không (tất cả phải tồn tại trong database)
     */
    private boolean isValidProductList(List<Long> productIds) {
        if (isListNullOrEmpty(productIds)) return true;
        return productRepository.countByIdIn(productIds) == productIds.size();
    }

    /**
     * Kiểm tra danh sách khách hàng có hợp lệ không (tất cả phải tồn tại trong database)
     */
    private boolean isValidCustomerList(List<Long> customerIds) {
        if (isListNullOrEmpty(customerIds)) return true;
        return customerRepository.countByIdIn(customerIds) == customerIds.size();
    }

    /**
     * Kiểm tra danh sách SKU có hợp lệ không (tất cả phải tồn tại trong database)
     */
    private boolean isValidProductDetailList(List<Long> productDetailIds) {
        if (isListNullOrEmpty(productDetailIds)) return true;
        return productDetailRepository.countByIdIn(productDetailIds) == productDetailIds.size();
    }

    /**
     * Kiểm tra danh sách có null hoặc rỗng không
     */
    private boolean isListNullOrEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    /**
     * Thêm lỗi vào context
     */
    private void addViolation(ConstraintValidatorContext context, String field, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(field)
                .addConstraintViolation();
    }
}
