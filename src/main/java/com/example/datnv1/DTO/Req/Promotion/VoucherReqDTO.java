package com.example.datnv1.DTO.Req.Promotion;

import com.example.datnv1.Enum.DiscountType;
import com.example.datnv1.Enum.VoucherType;
import com.example.datnv1.Validation.Promotion.Voucher.ValidVoucher;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ValidVoucher
public class VoucherReqDTO {

    @NotBlank(message = "Tên voucher không được để trống")
    @Size(min = 3, max = 100, message = "Tên voucher phải từ 3 - 100 ký tự")
    private String name;

    @NotBlank(message = "Mã voucher không được để trống")
    @Pattern(regexp = "^[A-Z0-9]{5,10}$", message = "Mã voucher chỉ chứa chữ in hoa và số, độ dài 5-10 ký tự")
    private String code;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 1, message = "Số lượng voucher phải lớn hơn 0")
    private Integer quantity;

    @Min(value = 0, message = "Giá trị giảm tối đa không được nhỏ hơn 0")
    private Double maxDiscount;

    @Min(value = 0, message = "Giá trị đơn hàng tối thiểu không được nhỏ hơn 0")
    private Double minBill;

    @NotNull(message = "Giá trị giảm giá không được để trống")
    @Min(value = 1, message = "Giá trị giảm giá phải lớn hơn 0")
    private Double value;

    @NotNull(message = "Loại voucher không được để trống")
    private VoucherType voucherType;

    @NotNull(message = "Loại giảm giá không được để trống")
    private DiscountType discountType;

    @NotNull(message = "Thời gian bắt đầu không được để trống")
    @Future(message = "Thời gian bắt đầu phải lớn hơn thời gian hiện tại")
    private LocalDateTime startAt;

    @NotNull(message = "Thời gian kết thúc không được để trống")
    private LocalDateTime endAt;

    @NotBlank(message = "Trạng thái không được để trống")
    @Pattern(regexp = "ACTIVE|EXPIRED|DISABLED", message = "Trạng thái không hợp lệ")
    private String status;

    @Nullable
    private boolean applyToAllProducts = true;

    private List<Long> applicableProductIds;
    private List<Long> applicableCustomerIds;
    private List<Long> applicableProductDetailIds;
}
