package com.example.test.service;

import com.example.test.dto.ProvidersDTO;
import com.example.test.models.ProvidersNCC;
import com.example.test.serviceImpl.IProviderService;

import java.util.List;

public class ProviderNCCService implements IProviderService {
    @Override
    public List<ProvidersDTO> getAll() {
        return null;
    }

    @Override
    public ProvidersDTO getById(Integer id) {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public boolean save(ProvidersNCC stockDetail) {
        return false;
    }
}
