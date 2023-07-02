package com.khangnlg.config;

import com.khangnlg.entities.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class UserCustomFieldSetMapper implements FieldSetMapper<UserEntity> {


    @Override
    public UserEntity mapFieldSet(FieldSet fieldSet) throws BindException {
        UserEntity userEntity = new UserEntity();
        userEntity.setAddress(fieldSet.readString("address"));
        if(StringUtils.isEmpty(fieldSet.readString("dateOfBirth"))){
            userEntity.setDateOfBirth(0);
        }else{
            userEntity.setDateOfBirth(Long.parseLong(fieldSet.readString("dateOfBirth")));
        }
        userEntity.setEmail(fieldSet.readString("email"));
        userEntity.setGender(fieldSet.readString("gender"));
        userEntity.setGrantedAuthority(fieldSet.readString("grantedAuthority"));
        userEntity.setName(fieldSet.readString("name"));
        userEntity.setPassword(fieldSet.readString("password"));
        return userEntity;
    }
}
