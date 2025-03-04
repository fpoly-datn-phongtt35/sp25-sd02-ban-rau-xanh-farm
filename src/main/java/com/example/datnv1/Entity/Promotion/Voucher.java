package com.example.datnv1.Entity.Promotion;

import com.example.datnv1.Entity.Account.Customer;
import com.example.datnv1.Entity.BaseEntity;
import com.example.datnv1.Entity.Product.Product;
import com.example.datnv1.Entity.Product.ProductDetail;
import com.example.datnv1.Enum.DiscountType;
import com.example.datnv1.Enum.VoucherType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "voucher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Voucher extends BaseEntity {

    private String name;

    private String code;

    private Integer quantity;

    private Double maxDiscount;

    private Double minBill;

    @Enumerated(EnumType.STRING)
    private VoucherType voucherType;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    private Double value;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private String status;

    // Chỉ áp dụng nếu type = 2 (Voucher sản phẩm)
    @ManyToMany
    @JoinTable(
            name = "voucher_products",
            joinColumns = @JoinColumn(name = "voucher_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> applicableProducts;

    // Chỉ áp dụng nếu type = 2 (Voucher sản phẩm)
    @ManyToMany
    @JoinTable(
            name = "voucher_users",
            joinColumns = @JoinColumn(name = "voucher_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Customer> applicableCustomers; // Chỉ áp dụng nếu type = 3 (Voucher riêng tư)

    @ManyToMany
    @JoinTable(
            name = "voucher_product_skus",
            joinColumns = @JoinColumn(name = "voucher_id"),
            inverseJoinColumns = @JoinColumn(name = "sku_id") // Tham chiếu đến bảng SKU
    )
    private List<ProductDetail> applicableProductDetails; // Danh sách SKU áp dụng

}

