package com.example.datnv1.Service.ProductService;

import com.example.datnv1.DTO.Req.Product.ProductDetailBatchReqDTO;
import com.example.datnv1.DTO.Req.Product.ProductDetailReqDTO;
import com.example.datnv1.Entity.Product.Batch;
import com.example.datnv1.Entity.Product.ProductDetail;
import com.example.datnv1.Entity.Product.ProductDetailBatch;
import com.example.datnv1.Repository.ProductRepository.ProductDetailBatchRepo;
import com.example.datnv1.Repository.ProductRepository.ProductDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailService {

    @Autowired
    ProductDetailRepo productDetailRepo;

    @Autowired
    ProductDetailBatchRepo productDetailBatchRepo;

    @Autowired
    BatchSevice batchSevice;

    public void save(ProductDetailReqDTO productDetailReqDTO) {

        ProductDetail productDetail = new ProductDetail();
        productDetail.setProductDetailName(productDetailReqDTO.getName());
        productDetail.setWeight(productDetailReqDTO.getWeight());
        long sumQuantity = productDetailReqDTO
                    .getProductDetailBatch()
                    .stream()
                .mapToLong(ProductDetailBatchReqDTO::getQuatity)
                .sum();
        productDetail.setQuantity(sumQuantity);
        productDetailRepo.save(productDetail);

        productDetailReqDTO.getProductDetailBatch().forEach(item ->{

            ProductDetailBatch productDetailBatch = new ProductDetailBatch();
            productDetailBatch.setProductDetail(productDetail);
            Batch batch = batchSevice.getByCode(item.getBatchCode());
            productDetailBatch.setBatch(batch);
            productDetailBatch.setQuantity(item.getQuatity());
            productDetailBatchRepo.save(productDetailBatch);
        });
    }
}
