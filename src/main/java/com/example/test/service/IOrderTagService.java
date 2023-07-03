package com.example.test.service;

import com.example.test.entity.OrderTag;

import java.util.List;

public interface IOrderTagService {
    void save(OrderTag cp);
    List<OrderTag> findOrderTagByTagId(Integer id);
    boolean deleteOrderTagByOrderId (Integer orderId);
}
