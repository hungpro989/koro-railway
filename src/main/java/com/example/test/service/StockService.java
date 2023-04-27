package com.example.test.service;

import com.example.test.dto.StockCreateDTO;
import com.example.test.dto.StocksDTO;
import com.example.test.models.ProductDetail;
import com.example.test.models.StockDetail;
import com.example.test.models.Stocks;
import com.example.test.repository.*;
import com.example.test.serviceImpl.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
@Service
public class StockService implements IStockService {
    @Autowired
    StockRepository stockRepository;
    @Autowired
    private ProviderNCCRepository providerRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private StockDetailRepository stockDetailRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<StocksDTO> getAll() {
        List<StocksDTO> listDto = new ArrayList<>();
        List<Stocks> list = stockRepository.findAll();
        for(Stocks var: list){
            listDto.add((new StocksDTO(var)));
        }
        return listDto;
    }

    @Override
    public StocksDTO getById(Integer id) {
        StocksDTO dto = new StocksDTO();
        Stocks stocks = stockRepository.findById(id).orElse(null);
        if(stocks != null){
            dto = new StocksDTO(stocks);
            return dto;
        }
        return null;

    }

    @Override
    public boolean deleteById(Integer id) {
        try{
            stockRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean save(StockCreateDTO stockCreateDTO) {
        Stocks s = new Stocks(stockCreateDTO);
        if(stockCreateDTO.getProviderId()!=null){
            s.setProvidersNCC(providerRepository.findById(stockCreateDTO.getProviderId()).orElse(null)); //trạng thái đơn hàng
        }
        if(stockCreateDTO.getUserId()!=null){
            s.setUser(userRepository.findById(stockCreateDTO.getUserId()).orElse(null)); //người tạo
        }
        stockRepository.save(s);
        createStockDetail(stockCreateDTO,s);
        return  true;
    }
    public void createStockDetail(@RequestBody StockCreateDTO stockDTO, Stocks s){
        stockDTO.getStockDetailDTO().forEach(var -> {
            Integer idproDetail = var.getProductDetailId();
            System.out.println(idproDetail);
            ProductDetail productDetail = productDetailRepository.findById(var.getProductDetailId()).orElse(null);
            if (productDetail != null) {
                StockDetail stockDetail = new StockDetail(var);
                stockDetail.setStocks(s);
                stockDetail.setProductDetail(productDetail);
                stockDetailRepository.save(stockDetail);
            }
        });
    }
}
