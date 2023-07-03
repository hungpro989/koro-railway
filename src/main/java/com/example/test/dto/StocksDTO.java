package com.example.test.dto;
import com.example.test.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StocksDTO {
    private Integer id;
    private String note;
    private Integer totalProducts;
    private Float productMoney;
    private Float feeService;
    private Float feeDomesticShip;
    private Float feeInternationalShip;
    private Integer providerId;
    private Integer userId;
    private Date dateCreate;
    private Date dateCompletion;
    private Boolean status;
    private ProvidersDTO providersDTO;
    private UserOrderDTO userOrderDTO;
    private List<StockDetailDTO> stockDetailDTO;
    public StocksDTO(Stocks s) {
        this.id=s.getId();
        this.providerId=s.getProvidersNCC().getId();
        this.userId=s.getUser().getId();
        this.note=s.getNote();
        this.totalProducts=s.getTotalProducts();
        this.productMoney=s.getProductMoney();
        this.feeService=s.getFeeService();
        this.feeDomesticShip=s.getFeeDomesticShip();
        this.feeInternationalShip=s.getFeeInternationalShip();
        this.dateCreate=s.getDateCreate();
        this.dateCompletion=s.getDateCompletion();
        this.status=s.getStatus();
        //providers
        ProvidersNCC providersNCC = s.getProvidersNCC();
        this.providersDTO = new ProvidersDTO(providersNCC);
        //user
        User user = s.getUser();
        this.userOrderDTO = new UserOrderDTO(user);
        //lay danh sach stock detail
        List<StockDetail> stockDetail = s.getStockDetail();//lay ra
        List<StockDetailDTO> stockDetailDTO = new ArrayList<>();//tao mang moi
        stockDetail.forEach(p-> {
            stockDetailDTO.add(new StockDetailDTO(p));
        });
        this.stockDetailDTO = stockDetailDTO;
    }
}

