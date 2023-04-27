package com.example.test.serviceImpl;

import com.example.test.dto.UserDTO;
import com.example.test.dto.UserOrderDTO;
import com.example.test.models.User;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAll();

    List<UserOrderDTO> getAllUser();

    UserDTO getById(Integer id);
    boolean deleteById(Integer id);
    boolean save(User entity);
    boolean checkExistEmail(String email);
    boolean checkExistPhone(String phone);
    boolean checkExistId(Integer id);
    List<User> findEmployeeWhereNotId(Integer id, String email, String phone);

}
