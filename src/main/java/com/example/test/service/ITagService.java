

package com.example.test.service;

import com.example.test.dto.TagDTO;

import java.util.List;

public interface ITagService {
    List<TagDTO> getAll();
    TagDTO getById(Integer id);
}
