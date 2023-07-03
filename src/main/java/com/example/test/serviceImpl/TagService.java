package com.example.test.serviceImpl;

import com.example.test.dto.TagDTO;
import com.example.test.entity.Tag;
import com.example.test.repository.TagRepository;
import com.example.test.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService implements ITagService {
    @Autowired
    TagRepository TagRepository;
    @Override
    public List<TagDTO> getAll() {
        List<TagDTO> listDto = new ArrayList<>();
        List<Tag> list = TagRepository.findAll();
        for(Tag var: list){
            listDto.add((new TagDTO(var)));
        }
        return listDto;
    }

    @Override
    public TagDTO getById(Integer id) {
//        TagDTO dto = new OrderTypeDTO();
//        if(orderTypeRepository.findById(id).isPresent()){
//            OrderType orderType = orderTypeRepository.findById(id).get();
//            dto = new OrderTypeDTO(orderType);
//            return dto;
//        }
        return null;
    }



}
