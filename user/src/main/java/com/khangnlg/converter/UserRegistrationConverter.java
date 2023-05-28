package com.khangnlg.converter;

import com.khangnlg.entities.UserEntity;
import com.khangnlg.model.UserRegistrationModel;
import com.khangnlg.objectmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationConverter implements Converter<UserRegistrationModel, UserEntity> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity convertModelToEntity(UserRegistrationModel userRegistrationModel) {
        return UserEntity.builder()
                .email(userRegistrationModel.getEmail())
                .name(userRegistrationModel.getName())
                .dateOfBirth(userRegistrationModel.getDateOfBirth())
                .phone(userRegistrationModel.getPhone())
                .gender(userRegistrationModel.getGender())
                .address(userRegistrationModel.getAddress())
                .username(userRegistrationModel.getUsername())
                .grantedAuthority(userRegistrationModel.getGrantedAuthority())
                .password(passwordEncoder.encode(userRegistrationModel.getPassword()))
                .build();
    }

    @Override
    public UserRegistrationModel convertEntityToModel(UserEntity userEntity) {
        return null;
    }
}
