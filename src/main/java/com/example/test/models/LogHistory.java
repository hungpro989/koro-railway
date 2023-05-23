package com.example.test.models;

import com.example.test.dto.LogHistoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "log_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @CreationTimestamp
    @Column(name = "timestamp", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date timestamp;

    @Column(name = "level")
    private String level;

    @Column(name = "message")
    private String message;

    @Column(name = "logger")
    private String logger;

    public LogHistory (LogHistoryDTO c){
        this.id = c.getId();
        this.timestamp = c.getTimestamp();
        this.level = c.getLevel();
        this.message = c.getMessage();
        this.logger = c.getLogger();
    }
}
