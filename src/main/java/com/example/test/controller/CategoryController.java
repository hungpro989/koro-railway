package com.example.test.controller;

import com.example.test.dto.CategoryDTOAdmin;
import com.example.test.dto.ResponseObject;
import com.example.test.entity.Category;
import com.example.test.serviceImpl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public List<CategoryDTOAdmin> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ResponseObject> getCategoryById(@PathVariable Integer id) {
        if (categoryService.getCategoryById(id) == null) {
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Không tìm thấy category có id như trên", null));
        }
        return ResponseEntity.ok().body(new ResponseObject("success", "Lấy category thành công", categoryService.getCategoryById(id)));
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<ResponseObject> deleteCategory(@PathVariable Integer id) {
        if (categoryService.deleteById(id)) {
            return ResponseEntity.ok().body(new ResponseObject("success", "Xoa thanh cong", null));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Xoa that bai id = " + id, null));
    }

    @PostMapping("/category")
    public ResponseEntity<ResponseObject> createCategory(@RequestBody Category category) {
        if (categoryService.checkExistName(category.getName())) {
            try {
                categoryService.save(category);
                return ResponseEntity.ok().body(new ResponseObject("success", "Them danh muc thanh cong", category));
            } catch (Exception e) {
                return ResponseEntity.ok().body(new ResponseObject("error", "Them danh muc that bai", category));
            }
        }
        return ResponseEntity.ok().body(new ResponseObject("error", "Danh muc da ton tai", category));
    }

    @PutMapping("/category")
    public ResponseEntity<ResponseObject> update(@RequestBody Category category){
        if (categoryService.checkExistId(category.getId())) {
            CategoryDTOAdmin c = categoryService.getCategoryById(category.getId());
            boolean checkNameExist = categoryService.checkExistName(category.getName());
            if(checkNameExist){
                boolean check = categoryService.save(category);
                if(check) {
                    return ResponseEntity.ok().body(new ResponseObject("success", "Cap nhat danh muc thanh cong", category));
                }
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Cap nhat danh muc that bai", category));
            }else if(c.getName().equalsIgnoreCase(category.getName())){
//                boolean check = categoryService.save(category);
                if(categoryService.save(category)) {
                    return ResponseEntity.ok().body(new ResponseObject("success", "Cap nhat danh muc thanh cong", category));
                }
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Cap nhat danh muc that bai", category));
            }else {
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Ten danh muc da ton tai", category.getName()));
            }
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Id danh muc khong ton tai", category.getId()));
    }
}
