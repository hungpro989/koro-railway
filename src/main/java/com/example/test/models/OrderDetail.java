package com.example.test.models;

import com.example.test.dto.OrderDetailCreateDTO;
import com.example.test.dto.OrderDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "price")
    private Float price;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "quantity")
    private Integer quantity;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


    //quan he vs order
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="order_id")
    private Order orders;

    //quan hệ với product detail
    @ManyToOne
    @JoinColumn(name = "pro_de_id")
    private ProductDetail productDetail;

    public OrderDetail(OrderDetailDTO dto) {
        this.id = dto.getId();
        this.price =dto.getPrice();
        this.discount=dto.getDiscount();
        this.quantity = dto.getQuantity();
        this.createdAt = dto.getCreatedAt();
        this.updatedAt = dto.getUpdatedAt();
    }
    public OrderDetail(OrderDetailCreateDTO dto) {
        this.id = dto.getId();
        this.price =dto.getPrice();
        this.discount=dto.getDiscount();
        this.quantity = dto.getQuantity();
//        this.createdAt = dto.getCreatedAt();
//        this.updatedAt = dto.getUpdatedAt();
    }
}
