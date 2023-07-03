package com.example.test.service;

import com.example.test.dto.CustomerCreateDTO;
import com.example.test.dto.CustomerViewDTO;

import java.util.List;

public interface ICustomerService {
    List<CustomerViewDTO> getAll();
    CustomerViewDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(CustomerCreateDTO entity);
    boolean checkExistEmail(String email);
    CustomerCreateDTO checkExistPhone(String phone);
    boolean checkExistId(Integer id);

    boolean checkExistUsername(String username);
    List<CustomerViewDTO> getAllCustomersByPhone(String phone);
}
