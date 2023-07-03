package com.example.test.service;

import com.example.test.dto.ProductDetailDTO;
import com.example.test.entity.ProductDetail;

import java.util.List;

public interface IProductDetailService {
    ProductDetail save(ProductDetail productDetail);
    String copyProductDetail(Integer id);
    List<ProductDetailDTO> getAllProductDetail();
    ProductDetailDTO findProductDetailByCodeName(String s);
}
