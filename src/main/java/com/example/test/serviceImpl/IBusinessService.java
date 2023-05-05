package com.example.test.serviceImpl;

import com.example.test.dto.BusinessDTO;
import com.example.test.models.Business;

import java.util.List;

public interface IBusinessService {
    List<BusinessDTO> getAllBusiness();
    BusinessDTO getBusinessById(Integer id);
    boolean deleteById(Integer id);

    boolean save(BusinessDTO dto);

    boolean checkExistName(String name);
    boolean checkExistId(Integer id);
}
