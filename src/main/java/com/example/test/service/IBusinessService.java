package com.example.test.service;

import com.example.test.dto.BusinessDTO;

import java.util.List;

public interface IBusinessService {
    List<BusinessDTO> getAllBusiness();
    BusinessDTO getBusinessById(Integer id);
    boolean deleteById(Integer id);

    boolean save(BusinessDTO dto);

    boolean checkExistName(String name);
    boolean checkExistId(Integer id);
    BusinessDTO findBusinessByPageId(String id);

}
