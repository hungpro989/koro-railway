package com.example.test.controller;

import com.example.test.dto.ResponseObject;
import com.example.test.dto.StocksDTO;
import com.example.test.service.ProductDetailService;
import com.example.test.service.StockDetailService;
import com.example.test.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/stocks")
public class StockController {
    @Autowired
    StockService stockService;
    @Autowired
    StockDetailService stockDetailService;
    @Autowired
    ProductDetailService productDetailService;

    @GetMapping
    public ResponseEntity<ResponseObject> getList(){
        List<StocksDTO> listDto = stockService.getAll();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách stocks thành công", listDto));
        }
        return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách stocks thất bại, rỗng", listDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Integer id){
        StocksDTO dto = stockService.getById(id);
        if(dto!=null){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy stocks thành công", dto));
        }
        return ResponseEntity.ok().body(new ResponseObject("success", "Lấy stocks thất bại, rỗng", dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteStock(@PathVariable("id") Integer id){
        if(stockService.deleteById(id)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Xoá stocks thành công với id = ", id));
        };
        return ResponseEntity.ok().body(new ResponseObject("success", "Xoá stocks thất bại với id = ", id));
    }
    @PostMapping
    public ResponseEntity<ResponseObject> create(@RequestBody StocksDTO dto){
        if(stockService.save(dto)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Tạo stocks thành công", dto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Tạo stocks thất bại", null));
    }
    @PutMapping
    public ResponseEntity<ResponseObject> update(@RequestBody StocksDTO dto){
        if(stockService.save(dto)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Cập nhật stocks thành công", dto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Cập nhật stocks thất bại", null));
    }
}
