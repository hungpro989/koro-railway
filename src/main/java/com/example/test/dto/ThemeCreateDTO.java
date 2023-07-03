package com.example.test.dto;

import com.example.test.entity.Theme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThemeCreateDTO {
    private Integer id;
    private String name;
    private String value;
    private boolean status;
    public ThemeCreateDTO(Theme var) {
        this.id=var.getId();
        this.name = var.getName();
        this.value = var.getValue();
        this.status = var.isStatus();
    }
}
