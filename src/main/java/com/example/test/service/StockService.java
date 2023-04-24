package com.example.test.service;

import com.example.test.dto.StocksDTO;
import com.example.test.serviceImpl.IStockService;

import java.util.List;

public class StockService implements IStockService {
    @Override
    public List<StocksDTO> getAll() {
        return null;
    }

    @Override
    public StocksDTO getById(Integer id) {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public boolean save(StocksDTO stocksDTO) {
        return false;
    }
}
