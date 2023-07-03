package com.example.test.repository;

import com.example.test.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
    ProductDetail findProductDetailByCodeName (String s);
}
