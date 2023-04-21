package com.example.test.dto;

import com.example.test.models.Delivery;
import com.example.test.models.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDTO {
    private Integer id;
    private String codeName;
    private String name;
    private String token;
    private String dataStatus;

    @OneToMany(mappedBy = "delivery")
    private List<Order> order;
    public DeliveryDTO(Delivery d){
        this.id=d.getId();
        this.codeName=d.getCodeName();
        this.name=d.getName();
        this.token=d.getToken();
        this.dataStatus=d.getDataStatus();
    }
}
