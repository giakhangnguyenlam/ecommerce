package com.khangnlg.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /**
     * 3 types of category:
     * 1: category clothes
     * 2: category shoes;
     * 3: category accessories
     */
    private int category;
    private String name;
    private int quantity;
    private double price;
    private String description;
    private String image;

    @ManyToOne(targetEntity = StoreEntity.class)
    private long storeId;

    @OneToMany(mappedBy = "productId", orphanRemoval = true, cascade = CascadeType.ALL, targetEntity = CommentEntity.class)
    private List<CommentEntity> comments;

    @OneToOne(targetEntity = CategoryShoesEntity.class)
    private CategoryShoesEntity categoryshoes;

    @OneToOne(targetEntity = CategoryClothesEntity.class)
    private CategoryClothesEntity categoryclothes;

    @OneToOne(targetEntity = CategoryAccessoriesEntity.class)
    private CategoryAccessoriesEntity categoryaccessories;

}
