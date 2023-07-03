package com.example.test.repository;

import com.example.test.entity.TestGhn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestGhnRepository extends JpaRepository<TestGhn, Integer> {
}
