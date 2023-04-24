package com.example.test.serviceImpl;

import com.example.test.dto.StocksDTO;

import java.util.List;

public interface IStockService {
    List<StocksDTO> getAll();
    StocksDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(StocksDTO stocksDTO);
}
