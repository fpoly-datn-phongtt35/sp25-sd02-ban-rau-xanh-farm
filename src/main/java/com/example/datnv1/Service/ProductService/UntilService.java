package com.example.datnv1.Service.ProductService;

import com.example.datnv1.Entity.Product.Unit;
import com.example.datnv1.Repository.ProductRepository.UntilRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UntilService {

    @Autowired
    UntilRepo untilRepo;

    public Unit add(Unit u) {
        if(u != null && u.getName() != null) {
            u.setActive(true);
             return untilRepo.save(u);
        }
        throw new RuntimeException("Tên đơn vị còn trống!");
    }

    public Unit getById(Long id) {
        return untilRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn vị"));
    }
    public List<Unit> getAll() {
        return untilRepo.findAll();
    }
}
