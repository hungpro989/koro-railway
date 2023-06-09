package com.example.test.repository;

import com.example.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findEmployeeByPhone(String phone);
    User findByEmail(String email);
    @Query("select e from User e where e.id <> :id and e.phone= :phone or e.email= :email")
    List<User> getEmployeeWhereNotId(Integer id, String email, String phone);

    User findByUsername(String username);
}
