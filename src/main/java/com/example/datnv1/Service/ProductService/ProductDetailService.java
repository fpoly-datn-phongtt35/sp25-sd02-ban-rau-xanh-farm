package com.example.datnv1.Service.ProductService;

import com.example.datnv1.DTO.Req.Product.ProductDetailBatchReqDTO;
import com.example.datnv1.DTO.Req.Product.ProductDetailReqDTO;
import com.example.datnv1.Entity.Product.Batch;
import com.example.datnv1.Entity.Product.ProductDetail;
import com.example.datnv1.Entity.Product.ProductDetailBatch;
import com.example.datnv1.Repository.ProductRepository.ProductDetailBatchRepo;
import com.example.datnv1.Repository.ProductRepository.ProductDetailRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailService {

    @Autowired
    ProductDetailRepo productDetailRepo;

    @Autowired
    ProductDetailBatchRepo productDetailBatchRepo;

    @Autowired
    BatchSevice batchSevice;

    @Transactional
    public void save(ProductDetailReqDTO productDetailReqDTO) {

        ProductDetail productDetail = new ProductDetail();
        productDetail.setProductDetailName(productDetailReqDTO.getName());
        productDetail.setWeight(productDetailReqDTO.getWeight());
        int sumQuantity = productDetailReqDTO
                    .getProductDetailBatch()
                    .stream()
                .mapToInt(ProductDetailBatchReqDTO::getQuatity)
                .sum();
        productDetail.setQuantity(sumQuantity);
        productDetail.setPrice(productDetailReqDTO.getPrice());
        productDetailRepo.save(productDetail);

        productDetailReqDTO.getProductDetailBatch().forEach(item ->{

            ProductDetailBatch productDetailBatch = new ProductDetailBatch();
            productDetailBatch.setProductDetail(productDetail);
            Batch batch = batchSevice.getByCode(item.getBatchCode());
            productDetailBatch.setBatch(batch);
            productDetailBatch.setQuantity(item.getQuatity());
            batch.setQuantityRetail(batch.getQuantity() - item.getQuatity() * productDetail.getWeight());
            System.out.println(batch.getQuantity() + "-" + item.getQuatity());
            batchSevice.batchSave(batch);
            productDetailBatchRepo.save(productDetailBatch);
        });
    }
    @Transactional
    public void save(ProductDetailBatch productDetailBatch){
        productDetailBatchRepo.save(productDetailBatch);
    }

    public ProductDetailBatch getProductDetailBatchById(Long id) {
        return productDetailBatchRepo.findById(id)
                .orElseThrow(()-> new RuntimeException(" Không tìm thấy product detail batch !"));
    }

    public ProductDetail getByProductDetailId(Long id) {
        return productDetailRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Không tìm thấy sản phẩm chi tiết!"));
    }

    public List<ProductDetailBatch> findFirstByProductDetailAndAvailableStock(Long productDetailId) {
        return productDetailBatchRepo.findFirstByProductDetailAndAvailableStock(productDetailId);
    }
}
