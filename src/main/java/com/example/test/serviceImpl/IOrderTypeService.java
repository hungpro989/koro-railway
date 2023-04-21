

package com.example.test.serviceImpl;

import com.example.test.dto.OrderTypeDTO;
import com.example.test.models.OrderType;

import java.util.List;

public interface IOrderTypeService {
    List<OrderTypeDTO> getAll();
    OrderTypeDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(OrderType delivery);
    boolean checkExistName(String name);
    boolean checkExistId(Integer id);
}
