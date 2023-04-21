package com.example.test.serviceImpl;


import com.example.test.models.OrderDetail;

public interface IOrderDetailRepository {
    OrderDetail save(OrderDetail orderDetail);
    boolean deleteById(Integer id);

}
