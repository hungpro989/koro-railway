package com.example.test.service;

import com.example.test.dto.StockCreateDTO;
import com.example.test.dto.StocksDTO;

import java.util.List;

public interface IStockService {
    List<StocksDTO> getAll();
    StocksDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(StockCreateDTO stocksDTO);
    boolean changeQuantityStockComplete(StockCreateDTO stocksDTO);
}
