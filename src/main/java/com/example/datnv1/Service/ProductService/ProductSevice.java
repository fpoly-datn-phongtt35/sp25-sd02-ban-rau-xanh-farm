package com.example.datnv1.Service.ProductService;

import com.example.datnv1.DTO.Convert.ProductMapper;
import com.example.datnv1.DTO.Req.Product.ProductReqDTO;
import com.example.datnv1.DTO.Res.ProductResDTO;
import com.example.datnv1.Entity.Product.Product;
import com.example.datnv1.Entity.Product.Unit;
import com.example.datnv1.Repository.ProductRepository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSevice {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    BatchSevice batchSevice;

    @Autowired
    UntilService untilService;

    @Autowired
    ProductDetailService productDetailService;

    public Product add(ProductReqDTO productReqDTO) {

        Product product = new Product();
        product.setName(productReqDTO.getName());
        product.setPrice(productReqDTO.getPrice());
        product.setDescription(productReqDTO.getDescription());

        Unit unit = untilService.getById(productReqDTO.getUnitId());
        product.setUnit(unit);
        productRepo.save(product);

        productReqDTO.getBatchSet().forEach(item -> {
            item.setProduct(product);
            batchSevice.batchSave(item);
        });
        productReqDTO.getProductDetailSet().forEach(item -> {
            productDetailService.save(item);
        });
        return product;
    }

    public List<ProductResDTO> findAll() {
        List<Product> products = productRepo.findAll();
        return products
                .stream()
                .map(ProductMapper::convertProductToDTO)
                .toList();
    }
}
