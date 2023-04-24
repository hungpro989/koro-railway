package com.example.test.serviceImpl;

import com.example.test.dto.StockDetailDTO;
import com.example.test.models.StockDetail;

import java.util.List;

public interface IStockDetailService {
    List<StockDetailDTO> getAll();
    StockDetailDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(StockDetail stockDetail);
}
