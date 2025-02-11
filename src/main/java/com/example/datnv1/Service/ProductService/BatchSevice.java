package com.example.datnv1.Service.ProductService;

import com.example.datnv1.Entity.Product.Batch;
import com.example.datnv1.Repository.ProductRepository.BatchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BatchSevice {

    @Autowired
    BatchRepo batchRepo;

    public Batch batchSave(Batch batch) {
        return batchRepo.save(batch);
    }
    public Batch getByCode(UUID code){
        return batchRepo.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lô hàng") );
    }

}
