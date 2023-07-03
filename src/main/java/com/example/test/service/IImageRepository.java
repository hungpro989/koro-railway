package com.example.test.service;

import com.example.test.dto.ImageDTO;

import java.util.List;

public interface IImageRepository {
    List<ImageDTO> getAll();
    ImageDTO getById (Integer id);
    boolean deleteById(Integer id);
    boolean save(ImageDTO dto);

}
