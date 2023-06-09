package com.example.test.repository;

import com.example.test.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query(value = "SELECT DISTINCT a.province FROM Address a order by a.province ASC")
    List<String> getAllByProvince();
    @Query(value = "select DISTINCT a.district from Address a where (a.province = :province)order by a.district ASC")
    List<String> getAllByDistrict(String province);
    @Query(value = "select DISTINCT a.ward from Address a where (a.district = :district)order by a.ward ASC")
    List<String> getAllByWard(String district);
}
