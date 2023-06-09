package com.example.test.serviceImpl;

import com.example.test.dto.StockCreateDTO;
import com.example.test.dto.StocksDTO;
import com.example.test.entity.*;
import com.example.test.repository.*;
import com.example.test.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    @Autowired
    private ProductService productService;
    @Override
    public List<StocksDTO> getAll() {
        List<StocksDTO> listDto = new ArrayList<>();
        List<Stocks> list = stockRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
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
            StocksDTO dto = getById(id);
            dto.getStockDetailDTO().forEach(var->{
                ProductDetail pd = productDetailRepository.findById(var.getProductDetailDTO().getId()).orElse(null);
                productService.handleWhenCancelCreateStock(var.getQuantity(), pd);
            });
            stockRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean save(StockCreateDTO stockCreateDTO) {
        Stocks s = new Stocks(stockCreateDTO);
        if(stockCreateDTO.getProviderId() !=null){
            s.setProvidersNCC(providerRepository.findById(stockCreateDTO.getProviderId()).orElse(null));
        }else{
            s.setProvidersNCC(new ProvidersNCC(stockCreateDTO.getProvidersDTO()));
        }
        if(stockCreateDTO.getUserId()!=null){
            s.setUser(userRepository.findById(stockCreateDTO.getUserId()).orElse(null));
        }else{
            s.setUser(new User(stockCreateDTO.getUserOrderDTO()));
        }
        stockRepository.save(s);
        createStockDetail(stockCreateDTO,s);
        return  true;
    }

    @Override
    public boolean changeQuantityStockComplete(StockCreateDTO dto) {
                dto.getStockDetailDTO().forEach(var ->{
                    ProductDetail pd = productDetailRepository.findById(var.getProductDetailDTO().getId()).orElse(null);
                    productService.handleWhenCreateStock(var.getQuantity(), pd);
                });
                return true;
    }

    public void createStockDetail(@RequestBody StockCreateDTO stockDTO, Stocks s){
        stockDTO.getStockDetailDTO().forEach(var -> {
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
