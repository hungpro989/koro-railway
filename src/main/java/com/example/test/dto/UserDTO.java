package com.example.test.dto;

import com.example.test.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserDTO {
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
    private boolean sex;
    private String bankAccount;
    private String bankName;
    private String cccd;

    private Integer themeId;
    private Date createdAt;
    private Date updatedAt;
    private Set<String> listRole;

    public UserDTO(User b){
        this.id=b.getId();
        this.username = b.getUsername();
        this.password = b.getPassword();
        this.fullName=b.getFullName();
        this.address=b.getAddress();
        this.phone=b.getPhone();
        this.email=b.getEmail();
        this.image=b.getImage();
        this.description=b.getDescription();
        this.birthday=b.getBirthday();
        this.status=b.isStatus();
//        this.themeId =b.getThemes().getId();
        this.sex= b.isSex();
        this.bankAccount=b.getBankAccount();
        this.bankName = b.getBankName();
        this.cccd = b.getCccd();
        this.createdAt=b.getCreatedAt();
        this.updatedAt=b.getUpdatedAt();
    }
}
