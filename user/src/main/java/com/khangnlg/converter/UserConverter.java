package com.khangnlg.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khangnlg.entities.UserEntity;
import com.khangnlg.models.UserModel;
import com.khangnlg.objectmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<UserModel, UserEntity> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity convertModelToEntity(UserModel userModel) {
        return UserEntity.builder()
                .id(userModel.getId())
                .email(userModel.getEmail())
                .name(userModel.getName())
                .dateOfBirth(userModel.getDateOfBirth())
                .phone(userModel.getPhone())
                .gender(userModel.getGender())
                .address(userModel.getAddress())
                .orders(userModel.getOrders())
                .stores(userModel.getStores())
                .grantedAuthority(userModel.getGrantedAuthority())
                .password(passwordEncoder.encode(userModel.getPassword()))
                .build();

    }

    @Override
    public UserModel convertEntityToModel(UserEntity userEntity) {
        return UserModel
                .builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .address(userEntity.getAddress())
                .phone(userEntity.getPhone())
                .email(userEntity.getEmail())
                .dateOfBirth(userEntity.getDateOfBirth())
                .phone(userEntity.getPhone())
                .build();
    }

}
