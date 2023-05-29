package com.khangnlg.service;

import com.khangnlg.exception.ObjectNotValidException;
import com.khangnlg.model.UserLoginModel;
import com.khangnlg.model.UserRegistrationModel;
import com.khangnlg.models.Token;
import com.khangnlg.models.UserModel;

public interface UserService {

    public Token createUser(UserRegistrationModel userRegistrationModel) throws Exception;

    public Token validateUser(UserLoginModel userLoginModel) throws ObjectNotValidException;

    public UserModel getUserByUsername(String username);

}
