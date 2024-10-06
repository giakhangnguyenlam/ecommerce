package com.khangnlg.service.impl;

import com.khangnlg.converter.UserConverter;
import com.khangnlg.entities.UserEntity;
import com.khangnlg.models.UserModel;
import com.khangnlg.repositories.UserRepository;
import com.khangnlg.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    @Autowired
    private UserRepository userRepository;

    @MockBean
    @Autowired
    private UserConverter userConverter;

    @Test
    @Disabled
    public void isUserExistTest(){
        String username = "khanguser4";

        verify(userRepository).findByUserName(username);

    }

    @Test
    void testGetUserByUsername_WhenUserExists() {
        // Arrange
        String username = "testuser";
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);

        UserModel expectedUserModel = new UserModel();
        expectedUserModel.setUsername(username);

        when(userRepository.findByUserName(username)).thenReturn(Optional.of(userEntity));
        when(userConverter.convertEntityToModel(userEntity)).thenReturn(expectedUserModel);

        // Act
        UserModel actualUserModel = userService.getUserByUsername(username);

        // Assert
        Assertions.assertEquals(expectedUserModel, actualUserModel, "UserModel should match");
    }
}