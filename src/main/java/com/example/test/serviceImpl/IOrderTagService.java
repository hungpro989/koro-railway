package com.example.test.serviceImpl;

import com.example.test.models.OrderTag;

import java.util.List;

public interface IOrderTagService {
    void save(OrderTag cp);
    List<OrderTag> findOrderTagByTagId(Integer id);
    boolean deleteOrderTagByOrderId (Integer orderId);
}
