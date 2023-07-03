

package com.example.test.service;

import com.example.test.dto.OrderStatusDTO;
import com.example.test.entity.OrderStatus;

import java.util.List;

public interface IOrderStatusService {
    List<OrderStatusDTO> getAll();
    OrderStatusDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(OrderStatus delivery);
    boolean checkExistName(String name);
    boolean checkExistId(Integer id);
}
