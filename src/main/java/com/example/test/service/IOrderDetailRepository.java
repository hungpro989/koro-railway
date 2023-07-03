package com.example.test.service;


import com.example.test.dto.OrderDetailDTO;
import com.example.test.entity.OrderDetail;

public interface IOrderDetailRepository {
    OrderDetail save(OrderDetail orderDetail);
    boolean deleteById(Integer id);
    OrderDetailDTO findbyOrderIdAndProductDetailId( Integer productDetailId, Integer orderId);
}
