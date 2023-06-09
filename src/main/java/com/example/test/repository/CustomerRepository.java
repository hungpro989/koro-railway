package com.example.test.repository;

import com.example.test.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findCustomerByEmail(String email);

    Customer findCustomerByPhone(String phone);

    Customer findCustomerByUsername(String username);
    List<Customer> findCustomersByPhone(String phone);
}
