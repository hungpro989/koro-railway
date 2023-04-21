package com.example.test.repository;

import com.example.test.models.OrderDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDeliveryRepository extends JpaRepository<OrderDelivery, Integer> {
    OrderDelivery findOrderDeliveryByCodeDelivery (String s);
    OrderDelivery findOrderDeliveryByOrderId(Integer id);
}
