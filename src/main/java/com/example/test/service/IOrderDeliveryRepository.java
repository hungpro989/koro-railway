package com.example.test.service;

import com.example.test.dto.OrderDeliveryDTO;
import com.example.test.entity.OrderDelivery;

import java.util.List;

public interface IOrderDeliveryRepository {
    List<OrderDelivery> getAll();
    OrderDeliveryDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(OrderDeliveryDTO dto);
    boolean checkExistCodeDelivery(String s);
    OrderDeliveryDTO getByOrderId(Integer id);
}
