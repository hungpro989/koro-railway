package com.example.test.dto;

import com.example.test.entity.LogHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogHistoryDTO {
    private Integer id;

    private Date timestamp;

    private String level;

    private String message;

    private String logger;

    public LogHistoryDTO (LogHistory c){
        this.id=c.getId();
        this.timestamp = c.getTimestamp();
        this.level = c.getLevel();
        this.message = c.getMessage();
        this.logger = c.getLogger();
    }
}