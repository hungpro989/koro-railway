package com.example.test.controller;

import com.example.test.dto.*;
import com.example.test.models.ProvidersNCC;
import com.example.test.models.Theme;
import com.example.test.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/themes")
@CrossOrigin
public class ThemeController {
    @Autowired
    ThemeService themeService;
    @GetMapping
    public ResponseEntity<ResponseObject> getAll(){
        List<ThemeViewDTO> listDto = themeService.getAll();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách THEMES thành công", listDto));
        }
        return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách THEMES thất bại", null));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Integer id){
        ThemeViewDTO dto = themeService.findById(id);
        if(dto!=null){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy THEMES thành công", dto));
        }
        return ResponseEntity.ok().body(new ResponseObject("success", "Lấy THEMES thất bại, rỗng", dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteBusiness(@PathVariable("id") Integer id){
        if(themeService.deleteById(id)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Xoá THEMES thành công với id = "+id, id));
        };
        return ResponseEntity.ok().body(new ResponseObject("success", "Xoá THEMES thất bại với id = "+id, id));
    }
    @PostMapping
    public ResponseEntity<ResponseObject> create(@RequestBody ThemeCreateDTO dto){
        Theme theme = new Theme(dto);
        if(themeService.save(theme)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Tạo THEMES mới thành công", theme));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Tạo THEMES thất bại", null));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@RequestBody ThemeCreateDTO dto, @PathVariable Integer id){
        Theme theme = new Theme(dto);
        if (themeService.save(theme)) {
            return ResponseEntity.ok().body(new ResponseObject("success", "Cập nhật THEMES thành công", theme));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Cập nhật THEMES thất bại", null));
    }
}
