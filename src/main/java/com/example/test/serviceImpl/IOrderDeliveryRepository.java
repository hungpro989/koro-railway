package com.example.test.serviceImpl;

import com.example.test.dto.OrderDeliveryDTO;
import com.example.test.models.OrderDelivery;

import java.util.List;

public interface IOrderDeliveryRepository {
    List<OrderDelivery> getAll();
    OrderDeliveryDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(OrderDeliveryDTO dto);
    boolean checkExistCodeDelivery(String s);
    OrderDeliveryDTO getByOrderId(Integer id);
}
