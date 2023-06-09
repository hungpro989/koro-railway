package com.example.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "category_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //qhe voi category
    @ManyToOne
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private Category category;

    //qhe voi product
    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;

}
