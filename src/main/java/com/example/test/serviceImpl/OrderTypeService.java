package com.example.test.serviceImpl;

import com.example.test.dto.OrderTypeDTO;
import com.example.test.entity.OrderType;
import com.example.test.repository.OrderTypeRepository;
import com.example.test.service.IOrderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderTypeService implements IOrderTypeService {
    @Autowired
    OrderTypeRepository orderTypeRepository;
    @Override
    public List<OrderTypeDTO> getAll() {
        List<OrderTypeDTO> listDto = new ArrayList<>();
        List<OrderType> list = orderTypeRepository.findAll();
        for(OrderType var: list){
            listDto.add((new OrderTypeDTO(var)));
        }
        return listDto;
    }

    @Override
    public OrderTypeDTO getById(Integer id) {
        OrderTypeDTO dto = new OrderTypeDTO();
        if(orderTypeRepository.findById(id).isPresent()){
            OrderType orderType = orderTypeRepository.findById(id).get();
            dto = new OrderTypeDTO(orderType);
            return dto;
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        try{
            orderTypeRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean save(OrderType orderType) {
        try{
            orderTypeRepository.save(orderType);
            return  true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean checkExistName(String name) {
        if (orderTypeRepository.findOrderTypeByName(name)!=null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkExistId(Integer id) {
        if(orderTypeRepository.findById(id).orElse(null)!=null){
            return true;
        }
        return false;
    }
}
