package com.example.test.controller;

import com.example.test.dto.ProvidersDTO;
import com.example.test.dto.ResponseObject;
import com.example.test.models.ProvidersNCC;
import com.example.test.service.ProviderNCCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/providers")
@CrossOrigin

public class ProviderController {
    @Autowired
    ProviderNCCService providerService;
    @GetMapping
    public ResponseEntity<ResponseObject> getList(){
        List<ProvidersDTO> listDto = providerService.getAll();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách provider thành công", listDto));
        }
        return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách provider thất bại, rỗng", listDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Integer id){
        ProvidersDTO dto = providerService.getById(id);
        if(dto!=null){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy provider thành công", dto));
        }
        return ResponseEntity.ok().body(new ResponseObject("success", "Lấy provider thất bại, rỗng", dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteBusiness(@PathVariable("id") Integer id){
        if(providerService.deleteById(id)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Xoá provider thành công với id = ", id));
        };
        return ResponseEntity.ok().body(new ResponseObject("success", "Xoá provider thất bại với id = ", id));
    }
    @PostMapping
    public ResponseEntity<ResponseObject> create(@RequestBody ProvidersDTO dto){
        ProvidersNCC p = new ProvidersNCC(dto);
        if(providerService.save(p)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Tạo provider mới thành công", p));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Tạo provider thất bại1", null));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@RequestBody ProvidersDTO dto, @PathVariable Integer id){
        ProvidersNCC providers = new ProvidersNCC(dto);
        if (providerService.save(providers)) {
            return ResponseEntity.ok().body(new ResponseObject("success", "Cap nhat provider thanh cong", providers));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Cap nhat provider khong thanh cong", null));
    }
}
