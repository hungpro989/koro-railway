package com.example.test.serviceImpl;

import com.example.test.dto.SettingDTO;
import com.example.test.models.Setting;

import java.util.List;

public interface ISettingRepository {
    boolean save(Setting setting);
    List<SettingDTO> getAllSetting();
    boolean deleteById(Integer id);
    SettingDTO findSettingById(Integer id);
    SettingDTO findSettingByKey(String s);
    boolean updateSetting(SettingDTO settingDTO, String key);

}
