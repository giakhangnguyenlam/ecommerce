package com.khangnlg.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "store")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nameStore;
    private String storeDescription;
    private String image;

    @ManyToOne(targetEntity = UserEntity.class)
    private int userId;

    @OneToMany(mappedBy = "storeId", orphanRemoval = true, cascade = CascadeType.ALL, targetEntity = ProductEntity.class)
    private List<ProductEntity> products;

}
