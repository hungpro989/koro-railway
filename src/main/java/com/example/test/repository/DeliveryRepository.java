package com.example.test.repository;

import com.example.test.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    Delivery findDeliveryByCodeName(String codeName);
    Delivery findDeliveryByName(String name);
}
