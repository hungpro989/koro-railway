package com.example.test.service;

import com.example.test.entity.CategoryProduct;

import java.util.List;

public interface ICategoryProductService {
    void save(CategoryProduct cp);
    void deleteById(Integer id);
    List<CategoryProduct> findCategoryProductByProductId(Integer id);
}
