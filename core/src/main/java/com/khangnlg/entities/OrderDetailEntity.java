package com.khangnlg.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "orderdetail")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int productId;
    private int quantity;
    private String description;
    private long date;
    private String productName;
    private double price;
    private String status;

    @ManyToOne(targetEntity = OrderEntity.class)
    private OrderEntity orderId;

}
