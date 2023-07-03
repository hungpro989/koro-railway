package com.example.test.serviceImpl;

import com.example.test.dto.SourceDTO;
import com.example.test.entity.Source;
import com.example.test.repository.SourceRepository;
import com.example.test.service.ISourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SourceService implements ISourceService {
    @Autowired
    SourceRepository sourceRepository;
    @Override
    public List<SourceDTO> getAll() {
        List<SourceDTO> listDto = new ArrayList<>();
        List<Source> list = sourceRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        for(Source var: list){
            listDto.add((new SourceDTO(var)));
        }
        return listDto;
    }

    @Override
    public SourceDTO getById(Integer id) {
        SourceDTO dto = new SourceDTO();
        Source m = sourceRepository.findById(id).orElse(null);
        if(m != null){
            dto = new SourceDTO(m);
            return dto;
        }
        return null;
    }
}
