package com.khangnlg.models;

import com.khangnlg.entities.OrderEntity;
import com.khangnlg.entities.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {
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
    private List<OrderEntity> orders;
    private List<StoreEntity> stores;
}
