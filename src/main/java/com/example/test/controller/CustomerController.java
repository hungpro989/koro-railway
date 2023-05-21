package com.example.test.controller;

import com.example.test.dto.CustomerCreateDTO;
import com.example.test.dto.CustomerViewDTO;
import com.example.test.dto.OrderDTO;
import com.example.test.dto.ResponseObject;
import com.example.test.models.Customer;
import com.example.test.service.CustomerService;
import com.example.test.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    OrderService orderService;
    @GetMapping
    public ResponseEntity<ResponseObject> getAll(){
        List<CustomerViewDTO> listDto = customerService.getAll();
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách khách hàng thành công", listDto));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Lấy danh sách khách hàng thất bại", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Integer id){
        if(customerService.checkExistId(id)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy khách hàng thành công", customerService.getById(id)));
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Không tìm thấy trang khách hàng có id như trên", null));
    }

    @PostMapping
    public ResponseEntity<ResponseObject> create(@RequestBody CustomerCreateDTO dto){
        Customer c = new Customer(dto);
        if(!customerService.checkExistEmail(dto.getEmail()) && customerService.checkExistPhone(dto.getPhone())!=null){
            if(!customerService.checkExistUsername(dto.getUsername())){
                if(customerService.save(dto)){
                    return ResponseEntity.ok().body(new ResponseObject("success", "Thêm khách hàng mới thành công", c));
                }
                    return ResponseEntity.badRequest().body(new ResponseObject("error", "Username đã tồn tại", null));
            }
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Thêm khách hàng mới thất bại1", null));
        }else{
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Email hoặc sdt đã được đăng ký", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@RequestBody CustomerCreateDTO customer, @PathVariable Integer id){
        CustomerViewDTO dto = customerService.getById(id);
        if(dto != null){
            //kiểm tra allEmployee nhưng phone, email là ko trùng ==> cập nhật luôn
            if(customerService.checkExistPhone(customer.getPhone())!=null && !customerService.checkExistEmail(customer.getEmail())) {
                if (customerService.save(customer)) {
                    return ResponseEntity.ok().body(new ResponseObject("success", "Cập nhật thông tin thành công", customer));
                }
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Cập nhật thông tin thất bại", null));
                //kiểm tra empPhone, empEmail trùng với dtoPhone, dtoEmail các trường khác cập nhật => cập nhật luôn
            }else if(customer.getPhone().equalsIgnoreCase(dto.getPhone()) && customer.getEmail().equalsIgnoreCase(dto.getEmail())) {
                if (customerService.save(customer)) {
                    return ResponseEntity.ok().body(new ResponseObject("success", "Cập nhật thông tin thành công", customer));
                }
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Cập nhật thông tin thất bại", null));
            }else if(!customerService.checkExistUsername(customer.getUsername())){
                if (customerService.save(customer)) {
                    return ResponseEntity.ok().body(new ResponseObject("success", "Cập nhật thông tin thành công", customer));
                }
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Cập nhật thông tin thất bại", null));
            }else {
                return ResponseEntity.badRequest().body(new ResponseObject("error", "Sdt hoặc email đã tồn tại", null));
            }
        }
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Không tìm thấy khách hàng nào với ID này", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable("id") Integer id){
        if(customerService.checkExistId(id)){
            customerService.deleteById(id);
            return ResponseEntity.ok().body(new ResponseObject("success", "Xoá khách hàng thành công", null));
        };
        return ResponseEntity.badRequest().body(new ResponseObject("error", "Id khách hàng không tồn tại", null));
    }
    @GetMapping("/find-customer-by-phone/{phone}")
    public ResponseEntity<ResponseObject> findCustomerByPhone(@PathVariable String phone){
        CustomerCreateDTO dto = customerService.checkExistPhone(phone);
        if (Objects.nonNull(dto)){
            return ResponseEntity.ok().body(new ResponseObject("success", "Kiểm tra thông tin KH bằng SĐT thành công", dto));
        }
        return ResponseEntity.ok().body(new ResponseObject("ok", "Không tìm thấy KH có sdt như trên", null));
    }
    @GetMapping("/order-by-customer/{id}")
    public ResponseEntity<ResponseObject> getAllByCustomer(@PathVariable Integer id){
        List<OrderDTO> listDto = orderService.getAllByCustomer(id);
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách đơn hàng của khách hàng thành công", listDto));
        }
        return ResponseEntity.ok().body(new ResponseObject("success", "Lấy danh sách đơn hàng của khách thất bại", null));
    }
    @GetMapping("/search-customer-by-phone/{phone}")
    public ResponseEntity<ResponseObject> getAllCustomersByPhone(@PathVariable String phone){
        List<CustomerViewDTO> listDto = customerService.getAllCustomersByPhone(phone);
        if(!listDto.isEmpty()){
            return ResponseEntity.ok().body(new ResponseObject("success", "Tìm khách hàng bằng sdt "+phone+" thành công", listDto));
        }
        return ResponseEntity.ok().body(new ResponseObject("success", "Tìm khách hàng bằng sdt "+phone+" thất bại", null));
    }
}
