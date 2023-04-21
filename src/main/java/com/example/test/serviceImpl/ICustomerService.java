package com.example.test.serviceImpl;

import com.example.test.dto.CustomerViewDTO;
import com.example.test.models.Customer;

import java.util.List;

public interface ICustomerService {
    List<CustomerViewDTO> getAll();
    CustomerViewDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(Customer entity);
    boolean checkExistEmail(String email);
    CustomerViewDTO checkExistPhone(String phone);
    boolean checkExistId(Integer id);

    boolean checkExistUsername(String username);
}
