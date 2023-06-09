package com.example.test.controller;

import com.example.test.dto.BusinessDTO;
import com.example.test.dto.ResponseObject;
import com.example.test.entity.Business;
import com.example.test.repository.BusinessRepository;
import com.example.test.serviceImpl.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/business")
@CrossOrigin
public class BusinessController {
    @Autowired
    BusinessService businessService;
    @Autowired
    private BusinessRepository businessRepository;

    @GetMapping
    public ResponseEntity<ResponseObject> getAllProduct(){
        List<BusinessDTO> listDto = businessService.getAllBusiness();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lay danh sach business thanh cong", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lay danh sach business that bai", null));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Integer id){
        if(businessService.checkExistId(id)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy sản phẩm thành công", businessService.getBusinessById(id)));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Không tìm thấy sản phẩm có id như trên", null));
    }
    @GetMapping("/page-id/{id}")
    public ResponseEntity<ResponseObject> getByPageId(@PathVariable String id){
        Business business = businessRepository.findBusinessByCodeName(id);
        if(business!=null){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy business thành công", business));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Không tìm thấy business  có id như trên", null));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteBusiness(@PathVariable("id") Integer id){
        if(businessService.checkExistId(id)){
            businessService.deleteById(id);
            return ResponseEntity.ok().body(new ResponseObject("success", "Xoá business thành công", null));
        };
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Id business không tồn tại", null));
    }
    @PostMapping
    public ResponseEntity<ResponseObject> create(@RequestBody BusinessDTO business){
        Business b = new Business(business);
        if(!businessService.checkExistName(business.getCodeName())){
            try{
                businessService.save(business);
                return ResponseEntity.ok().body(new ResponseObject("success", "Tạo business mới thành công", business));
            }catch (Exception e) {
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Tạo business thất bại1", null));
            }
        }else{
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Tên mã của business đã tồn tại", null));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@RequestBody BusinessDTO dto, @PathVariable Integer id){
        if (businessService.save(dto)) {
            return ResponseEntity.ok().body(new ResponseObject("success", "Cap nhat business thanh cong", dto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Cap nhat business that bai", null));
    }
}
