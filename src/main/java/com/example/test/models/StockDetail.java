package com.example.test.models;
import com.example.test.dto.OrderDetailDTO;
import com.example.test.dto.StockDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
@Entity
@Table(name = "stock_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "note")
    private String note;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price")
    private Float price;
    @Column(name = "total_money")
    private Float totalMoney;
    @Column(name = "link_order")
    private  String linkOrder;
    @Column(name = "status")
    private Integer status;
    //stocks
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="stock_id")
    private Stocks stocks;
    //product detail
    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;
    public StockDetail(StockDetailDTO dto) {
        this.id=dto.getId();
        this.note=dto.getNote();
        this.quantity=dto.getQuantity();
        this.price=dto.getPrice();
        this.totalMoney = dto.getTotalMoney();
        this.linkOrder = dto.getLinkOrder();
        this.status = dto.getStatus();
    }
}
