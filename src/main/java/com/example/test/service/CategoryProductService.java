package com.example.test.service;

import com.example.test.models.CategoryProduct;
import com.example.test.repository.CategoryProductRepository;
import com.example.test.serviceImpl.ICategoryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryProductService implements ICategoryProductService {
    @Autowired
    CategoryProductRepository categoryProductRepository;
    @Override
    public void save(CategoryProduct cp) {
        categoryProductRepository.save(cp);
    }
    @Override
    public void deleteById(Integer id) {
        categoryProductRepository.deleteCategoryProductByProductId(id);
    }

    @Override
    public List<CategoryProduct> findCategoryProductByProductId(Integer id) {

        return categoryProductRepository.findCategoryProductByProductId(id);
    }


}
