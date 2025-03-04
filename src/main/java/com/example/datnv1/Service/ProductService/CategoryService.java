package com.example.datnv1.Service.ProductService;

import com.example.datnv1.Entity.Product.Category;
import com.example.datnv1.Repository.ProductRepository.CategoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Transactional
    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    public Category getById(Long id) {
         return categoryRepo.findById(id)
                 .orElseThrow(()-> new RuntimeException("Không tim thấy danh mục"));
    }

    public List<Category> getAll() {
        return categoryRepo.findAll();
    }

}
