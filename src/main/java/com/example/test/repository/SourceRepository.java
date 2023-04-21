package com.example.test.repository;

import com.example.test.models.Source;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceRepository  extends JpaRepository<Source, Integer> {
}
