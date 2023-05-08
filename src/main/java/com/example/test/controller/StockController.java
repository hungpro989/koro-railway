package com.example.test.controller;

import com.example.test.dto.ResponseObject;
import com.example.test.dto.StockCreateDTO;
import com.example.test.dto.StocksDTO;
import com.example.test.service.ProductDetailService;
import com.example.test.service.ProductService;
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
    @Autowired
    ProductService productService;

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
    public ResponseEntity<ResponseObject> create(@RequestBody StockCreateDTO dto){
        System.out.println(dto) ;
        if(stockService.save(dto)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Tạo stocks thành công", dto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Tạo stocks thất bại", null));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@RequestBody StockCreateDTO dto){
        if(stockService.save(dto)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Cập nhật stocks thành công", dto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Cập nhật stocks thất bại", null));
    }
    @PostMapping("/change-quantity-stock-complete")
    public ResponseEntity<ResponseObject> changeQuantityStockComplete(@RequestBody StockCreateDTO dto){
        if(stockService.changeQuantityStockComplete(dto)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Cập nhật số lượng trong sản phẩm thành công", null));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Cập nhật số lượng trong sản phẩm thất bại", null));
    }
    @DeleteMapping("/stock-detail/{id}")
    public ResponseEntity<ResponseObject> deleteOrderDetail(@PathVariable Integer id){
        if(stockDetailService.deleteById(id)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Xoá stock detail thành công", null));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Xoá stock detail thất bại", null));
    }
}
