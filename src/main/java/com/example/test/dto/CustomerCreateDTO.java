package com.example.test.dto;

import com.example.test.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateDTO {
    private Integer id;

    private String username;

    private String password;

    private String fullName;

    private String address;

    private String phone;

    private String email;

    private String image;

    private String description;

    private Date birthday;

    private boolean status;

    private float height;

    private float weight;
    private String province;
    private String district;
    private String ward;
    private Boolean sex;

    public CustomerCreateDTO(Customer c) {
        this.id=c.getId();
        this.username = c.getUsername();
        this.password = c.getPassword();
        this.fullName=c.getFullName();
        this.address=c.getAddress();
        this.phone=c.getPhone();
        this.description = c.getDescription();
        this.weight = c.getWeight();
        this.email=c.getEmail();
        this.image=c.getImage();
        this.birthday=c.getBirthday();
        this.status = c.getStatus();
        this.height = c.getHeight();
        this.ward=c.getWard();
        this.district=c.getDistrict();
        this.province=c.getProvince();
        this.sex=c.getSex();
    }
}
