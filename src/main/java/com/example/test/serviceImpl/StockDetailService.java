package com.example.test.serviceImpl;

import com.example.test.dto.StockDetailDTO;
import com.example.test.entity.StockDetail;
import com.example.test.repository.StockDetailRepository;
import com.example.test.service.IStockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class StockDetailService implements IStockDetailService {
    @Autowired
    StockDetailRepository stockDetailRepository;
    @Override
    public List<StockDetailDTO> getAll() {
        List<StockDetailDTO> listDto = new ArrayList<>();
        List<StockDetail> list = stockDetailRepository.findAll();
        for(StockDetail var: list){
            listDto.add((new StockDetailDTO(var)));
        }
        return listDto;

    }

    @Override
    public StockDetailDTO getById(Integer id) {
        StockDetailDTO dto = new StockDetailDTO();
        StockDetail stockDetail = stockDetailRepository.findById(id).orElse(null);
        if(stockDetail!=null){
            dto = new StockDetailDTO(stockDetail);
            return dto;
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        stockDetailRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean save(StockDetail stockDetail) {
        try{
            stockDetailRepository.save(stockDetail);
            return  true;
        }catch (Exception e) {
            return false;
        }

    }
}
