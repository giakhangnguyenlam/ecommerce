package com.khangnlg.config;

import com.khangnlg.entities.UserEntity;
import org.springframework.batch.item.ItemProcessor;

public class UserProcessor implements ItemProcessor<UserEntity, UserEntity> {
    @Override
    public UserEntity process(UserEntity userEntity) throws Exception {
        return userEntity;
    }
}
