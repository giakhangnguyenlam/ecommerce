package com.khangnlg.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long orderDate;
    private double total;
    private String orderStatus;
    private String paymentStatus;
    private int shipperId;

    @ManyToOne(targetEntity = UserEntity.class)
    private UserEntity userId;

    @OneToMany(targetEntity = OrderDetailEntity.class, mappedBy = "orderId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetailEntity> orderDetails;
    
}
