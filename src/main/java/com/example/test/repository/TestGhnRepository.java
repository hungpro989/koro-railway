package com.example.test.repository;

import com.example.test.models.TestGhn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestGhnRepository extends JpaRepository<TestGhn, Integer> {
}
