package com.example.test.service;

import com.example.test.dto.LogHistoryDTO;
import com.example.test.entity.LogHistory;

import java.util.List;

public interface ILogHistoryService {
    List<LogHistoryDTO> findAll();
    List<LogHistoryDTO> findAllByLevel(String s);
    List<LogHistoryDTO> findAllByTime(String s);
    LogHistoryDTO findById(Integer id);
    boolean save(LogHistory lh);
    boolean deleteById(Integer id);
}
