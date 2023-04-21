

package com.example.test.serviceImpl;

import com.example.test.dto.SourceDTO;

import java.util.List;

public interface ISourceService {
    List<SourceDTO> getAll();
    SourceDTO getById(Integer id);
}
