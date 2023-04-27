package com.example.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockCreateDTO {
    private Integer id;
    private String note;
    private Integer totalProducts;
    private Float productMoney;
    private Float feeService;
    private Float feeDomesticShip;
    private Float feeInternationalShip;
    private Integer providerId;
    private Integer userId;
    private java.sql.Date dateCreate;
    private java.sql.Date dateCompletion;
    private Integer status;
    private List<StockDetailCreateDTO> stockDetailDTO;
}
