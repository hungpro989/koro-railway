package com.example.test.models;
import com.example.test.dto.StockCreateDTO;
import com.example.test.dto.StocksDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "stocks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "note")
    private String note;
    @Column(name = "total_products")
    private Integer totalProducts;
    @Column(name = "product_money")
    private Float productMoney;
    @Column(name = "fee_service")
    private Float feeService;
    @Column(name = "fee_domestic_ship")
    private Float feeDomesticShip;
    @Column(name = "fee_international_ship")
    private Float feeInternationalShip;
    @Column(name = "date_create")
    private Date dateCreate;
    @Column(name = "date_completion")
    private Date dateCompletion;
    @Column(name = "status")
    private Integer status;
    //provider
    @ManyToOne()
    @JoinColumn(name = "provider_id", nullable=false)
    private ProvidersNCC providersNCC;
    //user
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable=false)
    private User user;
    //stocks detail
    @OneToMany(mappedBy="stocks", cascade = CascadeType.ALL)
    private List<StockDetail> stockDetail;

    public Stocks(StocksDTO dto) {
        this.id =dto.getId();
        this.note =dto.getNote();
        this.totalProducts=dto.getTotalProducts();
        this.productMoney=dto.getProductMoney();
        this.feeService=dto.getFeeService();
        this.feeDomesticShip=dto.getFeeDomesticShip();
        this.feeInternationalShip=dto.getFeeInternationalShip();
        this.dateCreate=dto.getDateCreate();
        this.dateCompletion=dto.getDateCompletion();
        this.status=dto.getStatus();
    }

    public Stocks(StockCreateDTO dto) {
        this.id =dto.getId();
        this.note =dto.getNote();
        this.totalProducts=dto.getTotalProducts();
        this.productMoney=dto.getProductMoney();
        this.feeService=dto.getFeeService();
        this.feeDomesticShip=dto.getFeeDomesticShip();
        this.feeInternationalShip=dto.getFeeInternationalShip();
        this.dateCreate=dto.getDateCreate();
        this.dateCompletion=dto.getDateCompletion();
        this.status=dto.getStatus();
    }
}
