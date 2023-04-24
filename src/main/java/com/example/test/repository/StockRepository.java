package com.example.test.repository;

import com.example.test.models.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stocks, Integer> {
}
