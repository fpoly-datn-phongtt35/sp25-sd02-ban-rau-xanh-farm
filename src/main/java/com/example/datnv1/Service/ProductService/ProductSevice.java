package com.example.datnv1.Service.ProductService;

import com.example.datnv1.Entity.Product.Product;
import com.example.datnv1.Entity.Product.Unit;
import com.example.datnv1.Repository.ProductRepository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSevice {

    @Autowired
    ProductRepo productRepo;
    @Autowired
    UntilService untilService;

    public Product add(Product product) {

        Unit unit = untilService.getById(product.getUntilId());
        product.setUnit(unit);
        return productRepo.save(product);
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }
}
