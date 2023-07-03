package com.example.test.dto;
import com.example.test.entity.ProvidersNCC;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvidersDTO {
    private Integer id;
    private String name;
    private String note;
    private Integer status;
    public ProvidersDTO(ProvidersNCC p) {
        this.id=p.getId();
        this.name =p.getName();
        this.note=p.getNote();
        this.status=p.getStatus();
    }
}
