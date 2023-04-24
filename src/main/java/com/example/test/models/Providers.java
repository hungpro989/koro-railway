package com.example.test.models;
import com.example.test.dto.ProvidersDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "providers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Providers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "note")
    private String note;
    @Column(name = "status")
    private Integer status;
    
    @OneToMany(mappedBy = "providers")
    private List<Stocks> stocks;

    public Providers(ProvidersDTO p) {
        this.id=p.getId();
        this.name=p.getName();
        this.note=p.getNote();
        this.status=p.getStatus();
    }
}
