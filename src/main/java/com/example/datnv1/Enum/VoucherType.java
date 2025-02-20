package com.example.datnv1.Enum;

public enum VoucherType {
    SHOP_WIDE,         // Voucher toàn shop
    PRODUCT_SPECIFIC,  // Voucher áp dụng cho các sản phẩm được chọn (mức sản phẩm chung)
    PRODUCT_DETAIL,    // Voucher áp dụng cho sản phẩm chi tiết (các SKU, biến thể cụ thể)
    PRIVATE,           // Voucher riêng tư
    NEW_CUSTOMER       // Voucher cho khách hàng mới
}
