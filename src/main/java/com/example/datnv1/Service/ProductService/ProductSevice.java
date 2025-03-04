package com.example.datnv1.Service.ProductService;

import com.example.datnv1.DTO.Convert.ProductMapper;
import com.example.datnv1.DTO.Req.Product.ProductReqDTO;
import com.example.datnv1.DTO.Res.ProductResDTO;
import com.example.datnv1.Entity.Product.Category;
import com.example.datnv1.Entity.Product.Image;
import com.example.datnv1.Entity.Product.Product;
import com.example.datnv1.Entity.Product.Unit;
import com.example.datnv1.Repository.ProductRepository.ImageRepo;
import com.example.datnv1.Repository.ProductRepository.ProductRepo;
import com.example.datnv1.Specification.ProductSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
    CategoryService categoryService;

    @Autowired
    ImageRepo imageRepo;

    @Autowired
    ProductDetailService productDetailService;

    @Transactional
    public ProductResDTO add(ProductReqDTO productReqDTO) {

        Product product = new Product();
        product.setName(productReqDTO.getName());
        product.setPrice(productReqDTO.getPrice());
        product.setDescription(productReqDTO.getDescription());

        Unit unit = untilService.getById(productReqDTO.getUnitId());
        product.setUnit(unit);
        Category category = categoryService.getById(productReqDTO.getCategoryId());
        product.setCategory(category);

        productRepo.save(product);

        for(Image item : productReqDTO.getImages()) {
            imageRepo.save(Image.builder()
                            .product(product)
                            .url(item.getUrl())
                            .slot(item.getSlot())
                    .build());
        }
        productReqDTO.getBatchSet().forEach(item -> {
            item.setProduct(product);
            batchSevice.batchSave(item);
        });
        if(productReqDTO.getProductDetailSet() != null) {
            productReqDTO.getProductDetailSet().forEach(item -> {
                productDetailService.save(item);
            });
        }
        return ProductMapper.convertProductToDTO(product);
    }

    public List<ProductResDTO> findAll() {
        List<Product> products = productRepo.findAll();
        return products
                .stream()
                .map(ProductMapper::convertProductToDTO)
                .toList();
    }

    public Page<ProductResDTO> getByPage(int page,
                                   int size,
                                   String keyword) {

        Sort sort =  Sort.by(Sort.Order.desc("createdAt"));
        Pageable pageable = PageRequest.of(page, size,sort);
        Specification<Product> specification = Specification
                .where(ProductSpecification.hasProductName(keyword));
        Page<Product> productPage =productRepo.findAll(specification, pageable);
        return productPage.map(ProductMapper::convertProductToDTO);
    }


    public Product getById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Không tìm thấy sản phẩm!"));
    }
}
