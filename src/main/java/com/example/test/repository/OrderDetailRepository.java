package com.example.test.repository;

import com.example.test.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    OrderDetail findOrderDetailByProductDetail_IdAndOrders_id(Integer productDetailId, Integer orderId);
    void deleteOrderDetailByOrdersId(Integer id);
}
