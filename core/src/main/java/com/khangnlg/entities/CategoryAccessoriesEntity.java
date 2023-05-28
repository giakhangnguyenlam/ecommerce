package com.khangnlg.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;

import javax.persistence.*;
import java.util.List;

@Entity(name = "categoryaccessories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryAccessoriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /**
     * Some of types:
     * 1: băng trán
     * 2: băng cổ tay
     * 3: nón
     * 4: túi
     * 5: bình nước
     */
    private String type;
    @ElementCollection(targetClass = String.class)
    private List<String> color;
    private String brand;
    private String origin;
    private String material;
    @OneToOne(targetEntity = ProductEntity.class, mappedBy = "categoryaccessories")
    private ProductEntity productId;

}
