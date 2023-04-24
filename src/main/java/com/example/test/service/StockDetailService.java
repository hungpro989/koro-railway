package com.example.test.service;

import com.example.test.dto.StockDetailDTO;
import com.example.test.models.StockDetail;
import com.example.test.serviceImpl.IStockDetailService;

import java.util.List;

public class StockDetailService implements IStockDetailService {
    @Override
    public List<StockDetailDTO> getAll() {
        return null;
    }

    @Override
    public StockDetailDTO getById(Integer id) {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public boolean save(StockDetail stockDetail) {
        return false;
    }
}
