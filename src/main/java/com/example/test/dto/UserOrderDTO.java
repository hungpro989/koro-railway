package com.example.test.dto;

import com.example.test.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderDTO {
    private Integer id;
    private String username;
    private String fullName;
    private String address;
    private String phone;
    private String email;
    private String image;
    private String description;
    private Date birthday;
    private boolean status;
    private boolean sex;
    private String bankAccount;
    private String bankName;
    private String cccd;
    private Date createdAt;
    private Date updatedAt;

    public UserOrderDTO(User b){
        this.id=b.getId();
        this.username = b.getUsername();
        this.fullName=b.getFullName();
        this.address=b.getAddress();
        this.phone=b.getPhone();
        this.email=b.getEmail();
        this.image=b.getImage();
        this.description=b.getDescription();
        this.birthday=b.getBirthday();
        this.status=b.isStatus();
        this.sex= b.isSex();
        this.bankAccount=b.getBankAccount();
        this.bankName = b.getBankName();
        this.cccd = b.getCccd();
        this.createdAt=b.getCreatedAt();
        this.updatedAt=b.getUpdatedAt();
    }
}
