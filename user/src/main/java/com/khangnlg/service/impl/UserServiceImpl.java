package com.khangnlg.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khangnlg.entities.UserEntity;
import com.khangnlg.exception.ObjectNotValidException;
import com.khangnlg.exception.UserExistException;
import com.khangnlg.model.UserLoginModel;
import com.khangnlg.model.UserRegistrationModel;
import com.khangnlg.models.Token;
import com.khangnlg.models.UserModel;
import com.khangnlg.objectmapper.Converter;
import com.khangnlg.repositories.UserRepository;
import com.khangnlg.security.JWTService;
import com.khangnlg.service.UserService;
import com.khangnlg.validator.ObjectValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("userConverter")
    private Converter<UserModel, UserEntity> userConverter;

    @Autowired
    @Qualifier("userRegistrationConverter")
    private Converter<UserRegistrationModel, UserEntity> userRegistrationConverter;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ObjectValidator<UserLoginModel> userLoginModelObjectValidator;

    @Autowired
    private ObjectValidator<UserRegistrationModel> userRegistrationModelObjectValidator;

    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional
    @Override
    public Token createUser(UserRegistrationModel userRegistrationModel) throws Exception {

        logger.info("Create user with username"+userRegistrationModel.getUsername());

        userRegistrationModelObjectValidator.valid(userRegistrationModel);

        if(isUserExist(userRegistrationModel.getUsername())){
            throw new UserExistException("username "+ userRegistrationModel.getUsername()+"is existed");
        }

        UserEntity userEntity = userRegistrationConverter.convertModelToEntity(userRegistrationModel);
        userEntity = userRepository.save(userEntity);
        redisTemplate.opsForValue().set(userEntity.getUsername(), objectMapper.writeValueAsString(userEntity));
        Token token = jwtService.generateToken(userEntity.getUsername());

        return token;

    }

    private boolean isUserExist(String username) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUserName(username);
        return optionalUserEntity.isPresent();
    }

    @Override
    public Token validateUser(UserLoginModel userLoginModel) throws ObjectNotValidException {

        userLoginModelObjectValidator.valid(userLoginModel);

        try{
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userLoginModel.getUsername(), userLoginModel.getPassword(), null));
        }catch (AuthenticationException e){
            throw new AuthenticationCredentialsNotFoundException("Username or password is incorrect");
        }

        Token token = jwtService.generateToken(userLoginModel.getUsername());
        return token;

    }

    @Override
    public UserModel getUserByUsername(String username) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUserName(username);
        if(optionalUserEntity.isPresent()){
            return userConverter.convertEntityToModel(optionalUserEntity.get());
        }else {
            return null;
        }
    }

}
