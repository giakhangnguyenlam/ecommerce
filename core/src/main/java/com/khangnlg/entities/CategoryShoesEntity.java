package com.khangnlg.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "categoryshoes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryShoesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /**
     * Some of styles:
     * 1: đá bóng
     * 2: chạy bộ
     * 3: bóng rổ
     * 4: cầu lông
     */
    private String style;
    @ElementCollection(targetClass = Double.class)
    private List<Double> size;
    @ElementCollection(targetClass = String.class)
    private List<String> color;
    private double height;
    private double weight;
    private String material;
    private String sole;
    private String origin;
    private double warranty;
    private String gender;

    @OneToOne(targetEntity = ProductEntity.class, mappedBy = "categoryshoes")
    private ProductEntity productId;

}
