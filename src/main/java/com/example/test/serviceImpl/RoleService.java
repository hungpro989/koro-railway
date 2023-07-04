package com.example.test.serviceImpl;

import com.example.test.config.ERole;
import com.example.test.entity.Role;
import com.example.test.repository.RoleRepository;
import com.example.test.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class RoleService implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(ERole name) {
        return roleRepository.findByName(name);
    }
}
