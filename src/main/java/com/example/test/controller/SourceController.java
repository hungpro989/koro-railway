package com.example.test.controller;

import com.example.test.dto.ResponseObject;
import com.example.test.dto.SourceDTO;
import com.example.test.serviceImpl.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/v1/sources")
@CrossOrigin
public class SourceController {
    @Autowired
    SourceService sourceService;
    @GetMapping
    public ResponseEntity<ResponseObject> getAll(){
        List<SourceDTO> listDto = sourceService.getAll();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách Source bussiness thành công", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy danh sách Source bussiness thất bại", null));
    }
}
