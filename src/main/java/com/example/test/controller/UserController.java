package com.example.test.controller;

import com.example.test.dto.*;
import com.example.test.entity.User;
import com.example.test.repository.UserRepository;
import com.example.test.serviceImpl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
@Slf4j
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<ResponseObject> getAll(){
        List<UserDTO> listDto = userService.getAll();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách nhân viên thành công", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy danh sách nhân viên thất bại", null));
    }
    @GetMapping("/list-user")
    public ResponseEntity<ResponseObject> getAllUser(){
        List<UserOrderDTO> listDto = userService.getAllUser();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách nhân viên thành công", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy danh sách nhân viên thất bại", null));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Integer id){
        if(userService.checkExistId(id)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy nhân viên thành công", userService.getById(id)));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Không tìm thấy trang nhân viên có id như trên", null));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable("id") Integer id){
        if(userService.checkExistId(id)){
            userService.deleteById(id);
            return ResponseEntity.ok().body(new ResponseObject("success", "Xoá nhân viên thành công", null));
        };
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Id nhân viên không tồn tại", null));
    }
    @PostMapping
    public ResponseEntity<ResponseObject> create(@RequestBody UserDTO dto){
        User b = new User(dto);
        if(!userService.checkExistEmail(dto.getEmail()) && !userService.checkExistPhone(dto.getPhone())){
            try{
                userService.save(b);
                return ResponseEntity.ok().body(new ResponseObject("success", "Thêm nhân viên mới thành công", dto));
            }catch (Exception e) {
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Thêm nhân viên mới thất bại1", null));
            }
        }else{
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Email hoặc sdt đã được đăng ký", null));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@RequestBody UserOrderDTO userOrderDto, @PathVariable Integer id){
        System.out.println(userOrderDto);
        User user = new User(userOrderDto);
        System.out.println(user);
        if(user != null){
            //kiểm tra allEmployee nhưng phone, email là ko trùng ==> cập nhật luôn
            if(!userService.checkExistPhone(user.getPhone()) && !userService.checkExistEmail(user.getEmail())) {
                if (userService.save(user)!=null) {
                    return ResponseEntity.ok().body(new ResponseObject("success", "Cập nhật thông tin thành công1", user));
                }
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Cập nhật thông tin thất bại1", null));
                //kiểm tra empPhone, empEmail trùng với dtoPhone, dtoEmail các trường khác cập nhật => cập nhật luôn
            }else if(user.getPhone().equalsIgnoreCase(user.getPhone()) && user.getEmail().equalsIgnoreCase(user.getEmail())) {
                if (userService.save(user)!=null) {
                    return ResponseEntity.ok().body(new ResponseObject("success", "Cập nhật thông tin thành công2", user));
                }
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Cập nhật thông tin thất bại2", null));
            //Lỗi sdt, email trùng ==>error
            }else {
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Sdt hoặc email đã tồn tại", null));
            }
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Không tìm thấy nhân viên nào với ID này", null));
    }

    @GetMapping("/change-theme/{userId}/{themeId}")
    public ResponseEntity<ResponseObject> changeTheme(@PathVariable Integer userId, @PathVariable Integer themeId) {

        if(userService.changeTheme(userId, themeId)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Thay đổi theme thành công",null ));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Thay đổi theme thất bại", null));
    }

}
