package com.example.test.serviceImpl;

import com.example.test.dto.ProductCreateDTO;
import com.example.test.dto.ProductDTOAdmin;
import com.example.test.models.Product;

import java.util.List;

public interface IProductService {
    List<ProductDTOAdmin> getAllProduct();
    ProductDTOAdmin getProducById(Integer id);
    void deleteById(Integer id);
    boolean save(Product product);
    Product checkExistName(String name);
    boolean checkExistId(Integer id);
    //String create(ProductCreateDTO productCreateDTO, Integer id);

    String updateAndCheckProduct(ProductCreateDTO productCreateDTO, Integer id);
    String copyProduct(Integer id);
}
