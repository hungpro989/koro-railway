package com.example.test.entity;

import com.example.test.dto.UserDTO;
import com.example.test.dto.UserOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User {
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
    private boolean status;

    @Column(name = "sex")
    private boolean sex;
    @Column(name = "bank_account")
    private String bankAccount;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "cccd")
    private String cccd;

    //order
    @OneToMany(mappedBy = "user")
    private List<Order> order;

    //stocks
    @OneToMany(mappedBy = "user")
    private List<Stocks> stocks;
    //theme
    @ManyToOne()
    @JoinColumn(name = "theme_id", nullable=false)
    private Theme themes;

    //role
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> listRoles = new HashSet<>();

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public User(UserDTO dto){
        this.id = dto.getId();
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.fullName=dto.getFullName();
        this.address=dto.getAddress();
        this.phone=dto.getPhone();
        this.email=dto.getEmail();
        this.address=dto.getAddress();
        this.image=dto.getImage();
        this.description=dto.getDescription();
        this.birthday=dto.getBirthday();
        this.status=dto.isStatus();
        this.sex= dto.isSex();
        this.bankAccount=dto.getBankAccount();
        this.bankName = dto.getBankName();
        this.cccd = dto.getCccd();
        this.createdAt=dto.getCreatedAt();
        this.updatedAt=dto.getUpdatedAt();
    }

    public User(UserOrderDTO dto) {
        this.id = dto.getId();
        this.fullName=dto.getFullName();
        this.address=dto.getAddress();
        this.phone=dto.getPhone();
        this.email=dto.getEmail();
        this.address=dto.getAddress();
        this.image=dto.getImage();
        this.description=dto.getDescription();
        this.birthday=dto.getBirthday();
        this.status=dto.isStatus();
        this.sex= dto.isSex();
        this.bankAccount=dto.getBankAccount();
        this.bankName = dto.getBankName();
        this.cccd = dto.getCccd();
        this.createdAt=dto.getCreatedAt();
        this.updatedAt=dto.getUpdatedAt();
    }
}
