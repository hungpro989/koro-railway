package com.example.test.mapper;

import com.example.test.config.LoginResponse;
import com.example.test.dto.UserDTO;
import com.example.test.dto.UserOrderDTO;
import com.example.test.entity.User;

public class UserAdapter {
    public static UserOrderDTO userOrderDTOToUser(User u){
        new UserOrderDTO();
        return UserOrderDTO.builder()
                .id(u.getId())
                .username(u.getUsername())
                .email(u.getEmail())
                .phone(u.getPhone())
                .address(u.getAddress())
                .bankName(u.getBankName())
                .bankAccount(u.getBankAccount())
                .birthday(u.getBirthday())
                .cccd(u.getCccd())
                .description(u.getDescription())
                .fullName(u.getFullName())
                .image(u.getImage())
                .createdAt(u.getCreatedAt())
                .updatedAt(u.getUpdatedAt())
                .status(u.isStatus())
                .sex(u.isSex())
                .build();
    }
    public static UserDTO userDTOToUser(User u){
        new UserOrderDTO();
        return UserDTO.builder()
                .id(u.getId())
                .username(u.getUsername())
                .password(u.getPassword())
                .email(u.getEmail())
                .phone(u.getPhone())
                .address(u.getAddress())
                .bankName(u.getBankName())
                .bankAccount(u.getBankAccount())
                .birthday(u.getBirthday())
                .cccd(u.getCccd())
                .description(u.getDescription())
                .fullName(u.getFullName())
                .image(u.getImage())
                .createdAt(u.getCreatedAt())
                .updatedAt(u.getUpdatedAt())
                .status(u.isStatus())
                .sex(u.isSex())
                .build();
    }
    public static User userToUserDTO(UserDTO u){
        new User();
        return User.builder()
                .id(u.getId())
                .username(u.getUsername())
                .password(u.getPassword())
                .email(u.getEmail())
                .phone(u.getPhone())
                .address(u.getAddress())
                .bankName(u.getBankName())
                .bankAccount(u.getBankAccount())
                .birthday(u.getBirthday())
                .cccd(u.getCccd())
                .description(u.getDescription())
                .fullName(u.getFullName())
                .image(u.getImage())
                .createdAt(u.getCreatedAt())
                .updatedAt(u.getUpdatedAt())
                .status(u.isStatus())
                .sex(u.isSex())
                .build();
    }
}
