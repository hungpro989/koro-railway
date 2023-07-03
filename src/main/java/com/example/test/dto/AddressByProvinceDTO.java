package com.example.test.dto;

import com.example.test.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressByProvinceDTO {
    private Integer id;
    private String provinceName;

    public AddressByProvinceDTO(Address var) {
        this.provinceName = var.getProvince();
    }
}
