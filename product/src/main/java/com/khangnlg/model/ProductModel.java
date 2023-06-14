package com.khangnlg.model;

import com.khangnlg.entities.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductModel {

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
    public boolean isDiscount;
    public double discount;
    private long storeId;
    private List<CommentEntity> comments;
    private CategoryShoesEntity categoryshoes;
    private CategoryClothesEntity categoryclothes;
    private CategoryAccessoriesEntity categoryaccessories;

}
