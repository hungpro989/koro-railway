package com.example.test.service;

import com.example.test.dto.StockDetailDTO;
import com.example.test.entity.StockDetail;

import java.util.List;

public interface IStockDetailService {
    List<StockDetailDTO> getAll();
    StockDetailDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(StockDetail stockDetail);
}
