package com.example.test.dto;

import com.example.test.entity.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDTO {
    private Integer id;
    private String codeName;
    private String description;
    private String descShort;
    private Integer totalQuantity;
    private Integer validQuantity;
    private Integer holdQuantity;
    private Float price;
    private Float discount;
    private String image;
    private Boolean status;
    private Float cost;

    public ProductDetailDTO(ProductDetail pd){
        this.id = pd.getId();
        this.codeName = pd.getCodeName();
        this.description = pd.getDescription();
        this.descShort = pd.getDescShort();
        this.totalQuantity = pd.getTotalQuantity();
        this.validQuantity = pd.getValidQuantity();
        this.price = pd.getPrice();
        this.discount = pd.getDiscount();
        this.image = pd.getImage();
        this.holdQuantity = pd.getHoldQuantity();
        this.status = pd.getStatus();
        this.cost=pd.getCost();
    }
}
