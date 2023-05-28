package com.khangnlg.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private long dateOfBirth;
    private String email;
    private String address;
    private String gender;
    private String username;
    private String password;
    private String grantedAuthority;
    private String phone;

    @OneToMany(mappedBy = "userId", orphanRemoval = true, cascade = CascadeType.ALL, targetEntity = OrderEntity.class)
    private List<OrderEntity> orders;

    @OneToMany(mappedBy = "userId", orphanRemoval = true, cascade = CascadeType.ALL, targetEntity = StoreEntity.class)
    private List<StoreEntity> stores;



}
