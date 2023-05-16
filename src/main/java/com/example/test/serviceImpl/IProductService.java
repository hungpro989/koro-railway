package com.example.test.serviceImpl;

import com.example.test.dto.ProductCreateDTO;
import com.example.test.dto.ProductDTOAdmin;
import com.example.test.dto.ProductDetailCreateDTO;
import com.example.test.models.Product;
import com.example.test.models.ProductDetail;

import java.util.List;

public interface IProductService {
    List<ProductDTOAdmin> getAllProduct();
    ProductDTOAdmin getProducById(Integer id);

    void deleteById(Integer id);
    boolean save(Product product);
    Product checkExistName(String name);
    boolean checkExistId(Integer id);
    //String create(ProductCreateDTO productCreateDTO, Integer id);

    String updateAndCheckProduct(ProductCreateDTO productCreateDTO, Integer id);
    String copyProduct(Integer id);

    //giảm valid
    void scaleDownQuantityValid(Integer quantity, ProductDetailCreateDTO dto);
    //tăng valid
    void scaleUpQuantityValid(Integer quantity, ProductDetailCreateDTO dto);

    //tăng hold
    void scaleUpQuantityHold(Integer quantity, ProductDetailCreateDTO dto);
    //giảm hold
    void scaleDownQuantityHold(Integer quantity, ProductDetailCreateDTO dto);

    //tăng total_quantity
    void scaleUpTotalQuantity(Integer quantity, ProductDetailCreateDTO dto);
    //giảm total_quantity
    void scaleDownTotalQuantity(Integer quantity, ProductDetailCreateDTO dto);


    //xử lý số lượng sản phẩm khi đặt hàng:
    void handleWhenCreateOrder(Integer quantity, ProductDetail pd);

    //xử lý số lượng sản phẩm khi in đơn:
    void handleWhenPrintBillOrder(Integer quantity, ProductDetail dto);

    //xử lý số lượng sản phẩm khi hoàn hàng:
    void handleWhenReFundOrder(Integer quantity, ProductDetail dto);
    //xử lý số lượng sản phẩm khi tạo stock:
    void handleWhenCreateStock(Integer quantity, ProductDetail dto);
    //xử lý số lượng sản phẩm khi huỷ tạo stock:
    void handleWhenCancelCreateStock(Integer quantity, ProductDetail dto);


}
