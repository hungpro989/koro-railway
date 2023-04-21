package com.example.test.serviceImpl;

import com.example.test.dto.ProductDetailDTO;
import com.example.test.models.ProductDetail;

import java.util.List;

public interface IProductDetailService {
    ProductDetail save(ProductDetail productDetail);
    String copyProductDetail(Integer id);
    List<ProductDetailDTO> getAllProductDetail();
}
