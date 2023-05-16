package com.example.test.service;

import com.example.test.dto.ProductDetailDTO;
import com.example.test.models.ProductDetail;
import com.example.test.repository.ProductDetailRepository;
import com.example.test.serviceImpl.IProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDetailService implements IProductDetailService
{
    @Autowired
    ProductDetailRepository productDetailRepository;
    @Override
    public ProductDetail save(ProductDetail p) {
        return productDetailRepository.save(p);
    }

    @Override
    public String copyProductDetail(Integer id) {
        ProductDetail pd = productDetailRepository.findById(id).orElse(null);
        ProductDetail p = new ProductDetail(pd);
        p.setId(null);
        p.setCodeName(p.getCodeName()+"_copy");
        if(save(p)!=null){
            return "true";
        }
        return "false";
    }

    @Override
    public List<ProductDetailDTO> getAllProductDetail() {
        List<ProductDetailDTO> listDto = new ArrayList<>();
        List<ProductDetail> list = productDetailRepository.findAll();
        for (ProductDetail productDetail : list) {
            listDto.add(new ProductDetailDTO(productDetail));
        }
        return listDto;
    }

    @Override
    public ProductDetailDTO findProductDetailByCodeName(String s) {
        ProductDetail pd = productDetailRepository.findProductDetailByCodeName(s);
        ProductDetailDTO dto = new ProductDetailDTO(pd);
        if (dto != null) {
            return dto;
        }
        return null;
    }


}
