package com.example.test.entity;

import com.example.test.dto.ProductCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "content_short")
    private String contentShort;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "image")
    private String image;

    @Column(name = "link_order")
    private String linkOrder;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;

    //quan hệ với category
    @OneToMany(mappedBy="product", cascade = CascadeType.ALL)
    private List<CategoryProduct> categoryProduct;

    //quan hệ với product detail
    @OneToMany(mappedBy="products", cascade = CascadeType.ALL)
    private List<ProductDetail> productDetail;

    public Product(ProductCreateDTO dto) {
        this.id = dto.getId();
        this.content = dto.getContent();
        this.contentShort = dto.getContentShort();
        this.image = dto.getImage();
        this.linkOrder = dto.getLinkOrder();
        this.name = dto.getName();
        this.slug = dto.getSlug();
        this.status = dto.getStatus();
    }

}
