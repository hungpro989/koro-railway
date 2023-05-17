package com.example.test.serviceImpl;

import com.example.test.dto.DeliveryDTO;
import com.example.test.models.Delivery;

import java.util.List;

public interface IDeliveryService {
    List<DeliveryDTO> getAll();
    DeliveryDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(Delivery delivery);
    boolean checkExistName(String name);
    boolean checkExistId(Integer id);
    DeliveryDTO findByName(String name);
}
