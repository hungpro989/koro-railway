

package com.example.test.service;

import com.example.test.dto.OrderTypeDTO;
import com.example.test.entity.OrderType;

import java.util.List;

public interface IOrderTypeService {
    List<OrderTypeDTO> getAll();
    OrderTypeDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(OrderType delivery);
    boolean checkExistName(String name);
    boolean checkExistId(Integer id);
}
