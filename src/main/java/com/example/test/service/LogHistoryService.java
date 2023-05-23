package com.example.test.service;

import com.example.test.dto.LogHistoryDTO;
import com.example.test.models.LogHistory;
import com.example.test.repository.LogHistoryRepository;
import com.example.test.serviceImpl.ILogHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Service
public class LogHistoryService implements ILogHistoryService {
    @Autowired
    LogHistoryRepository logHistoryRepository;
    @Override
    public List<LogHistoryDTO> findAll() {
        List<LogHistoryDTO> listDto = new ArrayList<>();
        List<LogHistory> list = logHistoryRepository.findAll();
        for (LogHistory history: list) {
            listDto.add(new LogHistoryDTO(history));
        }
        return listDto;
    }

    @Override
    public List<LogHistoryDTO> findAllByLevel(String s) {
        List<LogHistoryDTO> listDto = new ArrayList<>();
        List<LogHistory> list = logHistoryRepository.findLogHistoryByLevel(s);
        for (LogHistory history: list) {
            listDto.add(new LogHistoryDTO(history));
        }
        return listDto;
    }

    @Override
    public List<LogHistoryDTO> findAllByTime(String s) {
        List<LogHistoryDTO> listDto = new ArrayList<>();
        List<LogHistory> list = logHistoryRepository.findLogHistoryByTimestamp(s);
        for (LogHistory history: list) {
            listDto.add(new LogHistoryDTO(history));
        }
        return listDto;
    }

    @Override
    public LogHistoryDTO findById(Integer id) {
        LogHistoryDTO dto = new LogHistoryDTO(Objects.requireNonNull(logHistoryRepository.findById(id).orElse(null)));
        return dto;
    }

    @Override
    public boolean save(LogHistory lh) {
        logHistoryRepository.save(lh);
        return true;
    }

    @Override
    public boolean deleteById(Integer id) {
        logHistoryRepository.deleteById(id);
        return true;
    }
}
