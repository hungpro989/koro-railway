package com.example.test.service;

import com.example.test.models.OrderDetail;
import com.example.test.repository.OrderDetailRepository;
import com.example.test.serviceImpl.IOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService implements IOrderDetailRepository {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }
    @Override
    public boolean deleteById(Integer id) {
        try {
            orderDetailRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }


}
