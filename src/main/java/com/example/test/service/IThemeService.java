package com.example.test.service;

import com.example.test.dto.ThemeViewDTO;
import com.example.test.entity.Theme;

import java.util.List;

public interface IThemeService {
    List<ThemeViewDTO> getAll();
    ThemeViewDTO findById(Integer id);
    boolean deleteById(Integer id);
    boolean save (Theme theme);
}
