package com.example.test.entity;

import com.example.test.dto.CustomerCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "height")
    private float height;

    @Column(name = "weight")
    private float weight;
    @Column(name="province")
    private String province;
    @Column(name="district")
    private String district;
    @Column(name = "sex")
    private Boolean sex;
    @Column(name="ward")
    private String ward;
    @OneToMany(mappedBy = "customer")
    private List<Order> order;

    @OneToMany(mappedBy = "customer")
    private List<CustomerAddress> customerAddresses;

    public Customer(CustomerCreateDTO c) {
        this.id=c.getId();
        this.username=c.getUsername();
        this.password=c.getPassword();
        this.fullName=c.getFullName();
        this.phone= c.getPhone();
        this.address= c.getAddress();
        this.email= c.getEmail();
        this.image=c.getImage();
        this.description=c.getDescription();
        this.height=c.getHeight();
        this.weight=c.getWeight();
        this.birthday=c.getBirthday();
        this.status= c.isStatus();
        this.province=c.getProvince();
        this.district=c.getDistrict();
        this.ward=c.getWard();
        this.sex=c.getSex();
    }
}
