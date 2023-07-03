package com.example.test.repository;

import com.example.test.entity.LogHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LogHistoryRepository extends JpaRepository<LogHistory, Integer> {
    List<LogHistory> findLogHistoryByLevel(String s);
    List<LogHistory> findLogHistoryByTimestamp(String s);
}
