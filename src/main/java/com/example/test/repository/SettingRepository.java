package com.example.test.repository;

import com.example.test.models.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Integer> {
    Setting findSettingByMyKey(String s);

}
