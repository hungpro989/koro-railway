package com.example.test.serviceImpl;

import com.example.test.dto.ProvidersDTO;
import com.example.test.models.ProvidersNCC;

import java.util.List;

public interface IProviderService {
    List<ProvidersDTO> getAll();
    ProvidersDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(ProvidersNCC stockDetail);
}
