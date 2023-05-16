package com.example.test.service;

import com.example.test.dto.TagDTO;
import com.example.test.dto.ThemeViewDTO;
import com.example.test.models.ProvidersNCC;
import com.example.test.models.Tag;
import com.example.test.models.Theme;
import com.example.test.repository.ThemeRepository;
import com.example.test.serviceImpl.IThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ThemeService implements IThemeService {
    @Autowired
    ThemeRepository themeRepository;
    @Override
    public List<ThemeViewDTO> getAll() {
        List<ThemeViewDTO> listDto = new ArrayList<>();
        List<Theme> list = themeRepository.findAll();
        for(Theme var: list){
            listDto.add((new ThemeViewDTO(var)));
        }
        return listDto;
    }

    @Override
    public ThemeViewDTO findById(Integer id) {
        ThemeViewDTO dto = new ThemeViewDTO();
        Theme theme = themeRepository.findById(id).orElse(null);
        if(theme!=null){
            dto = new ThemeViewDTO(theme);
            return dto;
        }
        return null;
    }
    @Override
    public boolean deleteById(Integer id) {
        try{
            themeRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean save(Theme theme) {
        try{
            themeRepository.save(theme);
            return  true;
        }catch (Exception e) {
            return false;
        }
    }
}
