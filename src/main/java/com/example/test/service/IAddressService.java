package com.example.test.service;

import com.example.test.dto.AddressDTO;
import com.example.test.entity.Address;

import java.util.List;

public interface IAddressService {
    List<AddressDTO> getAllAddress();
    List<String> getAllProvince();
    List<String> getAllDistrict(String province);
    List<String> getAllWard(String district);
    boolean checkExistName(String name);
    boolean checkExistId(Integer id);
    boolean save(Address address);
    Address getAddressById(Integer id);
}
