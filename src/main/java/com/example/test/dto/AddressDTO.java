package com.example.test.dto;

import com.example.test.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Integer id;
    private String province;
    private String district;
    private String ward;

    public AddressDTO(Address a){
        this.id=a.getId();
        this.province=a.getProvince();
        this.district = a.getDistrict();
        this.ward = a.getWard();
    }
}
