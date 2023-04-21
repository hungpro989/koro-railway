package com.example.test.serviceImpl;

import com.example.test.dto.CategoryDTOAdmin;
import com.example.test.models.Category;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTOAdmin> getAllCategory();
    CategoryDTOAdmin getCategoryById(Integer id);
    boolean deleteById(Integer id);
     boolean save(Category category);
     boolean checkExistName(String name);
     boolean checkExistId(Integer id);
}
