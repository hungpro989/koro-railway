package com.example.test.service;

import com.example.test.config.ERole;
import com.example.test.entity.Role;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(ERole name);
}
