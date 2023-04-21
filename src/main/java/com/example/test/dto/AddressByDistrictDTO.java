package com.example.test.dto;

import com.example.test.models.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressByDistrictDTO {
    private String districtName;

    public AddressByDistrictDTO(Address var) {
        this.districtName = var.getDistrict();
    }
}
