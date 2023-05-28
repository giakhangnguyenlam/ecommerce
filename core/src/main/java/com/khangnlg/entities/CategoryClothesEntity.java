package com.khangnlg.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "categoryclothes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryClothesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /**
     * Some type of clothes:
     * áo
     * quần
     * áo clb
     */
    private String type;
    private String brand;
    private String origin;
    @ElementCollection(targetClass = String.class)
    private List<String> size;
    @ElementCollection(targetClass = String.class)
    private List<String> color;
    private String material;
    private String gender;

    @OneToOne(targetEntity = ProductEntity.class, mappedBy = "categoryclothes")
    private ProductEntity productId;

}
