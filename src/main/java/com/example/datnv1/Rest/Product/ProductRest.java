package com.example.datnv1.Rest.Product;

import com.example.datnv1.DTO.ApiResponse;
import com.example.datnv1.DTO.Req.Product.ProductReqDTO;
import com.example.datnv1.Entity.Product.Product;
import com.example.datnv1.Entity.Product.Unit;
import com.example.datnv1.Service.ProductService.ProductSevice;
import com.example.datnv1.Service.ProductService.UntilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductRest {

    @Autowired
    UntilService untilService;
    @Autowired
    ProductSevice productSevice;


    @PostMapping("/add-unit")
    public ResponseEntity<?> addUntil(@RequestBody Unit unit) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResponse.success(untilService.add(unit), "Thêm mới đơn vị thành công!"));
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(ApiResponse.error(HttpStatus.CONFLICT,e.getMessage(), e.getMessage()));
        }
    }

    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestBody ProductReqDTO productReqDTO) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResponse.success(productSevice.add(productReqDTO), "Thêm mới sản phẩm"));
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(ApiResponse.error(HttpStatus.CONFLICT,e.getMessage(), e.getMessage()));
        }
    }

    @GetMapping("get-alll")
    public ResponseEntity<?> getAllL() {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResponse.success(productSevice.findAll(), "Lấy danh sách sản phẩm thành công!"));
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(ApiResponse.error(HttpStatus.CONFLICT,e.getMessage(), e.getMessage()));
        }
    }
}
