package com.example.test.repository;

import com.example.test.entity.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTypeRepository extends JpaRepository<OrderType, Integer> {
    OrderType findOrderTypeByName(String name);

}
