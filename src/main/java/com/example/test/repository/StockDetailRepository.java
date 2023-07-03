package com.example.test.repository;

import com.example.test.entity.StockDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDetailRepository extends JpaRepository<StockDetail, Integer> {
}
