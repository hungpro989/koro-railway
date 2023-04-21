package com.example.test.serviceImpl;

import com.example.test.models.CategoryProduct;

import java.util.List;

public interface ICategoryProductService {
    void save(CategoryProduct cp);
    void deleteById(Integer id);
    List<CategoryProduct> findCategoryProductByProductId(Integer id);
}
