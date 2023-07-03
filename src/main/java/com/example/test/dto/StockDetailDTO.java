package com.example.test.dto;
import com.example.test.entity.ProductDetail;
import com.example.test.entity.StockDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDetailDTO {
    private Integer id;
    private String note;
    private Integer stockId;
    private Integer productDetailId;
    private Integer quantity;
    private Float price;
    private Float totalMoney;
    private  String linkOrder;
    private Integer status;
    private ProductDetailDTO productDetailDTO;
    public StockDetailDTO(StockDetail s) {
        this.id=s.getId();
        this.note=s.getNote();
        this.stockId=s.getStocks().getId();
        this.productDetailId=s.getProductDetail().getId();
        this.quantity=s.getQuantity();
        this.price=s.getPrice();
        this.totalMoney=s.getTotalMoney();
        this.linkOrder=s.getLinkOrder();
        this.status=s.getStatus();
        ProductDetail productDetail1 = s.getProductDetail();
        this.productDetailDTO= new ProductDetailDTO(productDetail1);
    }
}