package com.example.test.models;

import com.example.test.dto.ThemeCreateDTO;
import com.example.test.dto.ThemeViewDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "themes")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private String value;
    @Column(name = "status")
    private boolean status;
    @OneToMany(mappedBy = "themes")
    private List<User> users;

    public Theme(ThemeCreateDTO dto) {
        this.id=dto.getId();
        this.name = dto.getName();
        this.value = dto.getValue();
        this.status = dto.isStatus();
    }
    public Theme(ThemeViewDTO dto) {
        this.id=dto.getId();
        this.name = dto.getName();
        this.value = dto.getValue();
        this.status = dto.isStatus();
    }
}
