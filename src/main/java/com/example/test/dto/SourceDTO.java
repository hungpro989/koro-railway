package com.example.test.dto;

import com.example.test.entity.Source;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SourceDTO {
    private Integer id;
    private String name;
    private Boolean status;
    private String dataAttribute;

    public SourceDTO(Source s) {
        this.id=s.getId();
        this.name=s.getName();
        this.status=s.getStatus();
        this.dataAttribute=s.getDataAttribute();
    }
}
