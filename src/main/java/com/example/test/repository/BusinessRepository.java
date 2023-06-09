package com.example.test.repository;

import com.example.test.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Integer> {
    Business findBusinessByCodeName(String name);
    Business findBusinessByPageId(String pageId);
}
