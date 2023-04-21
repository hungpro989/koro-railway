

package com.example.test.serviceImpl;

import com.example.test.dto.OrderStatusDTO;
import com.example.test.models.OrderStatus;

import java.util.List;

public interface IOrderStatusService {
    List<OrderStatusDTO> getAll();
    OrderStatusDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(OrderStatus delivery);
    boolean checkExistName(String name);
    boolean checkExistId(Integer id);
}
