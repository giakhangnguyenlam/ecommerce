package com.khangnlg.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khangnlg.entities.UserEntity;
import com.khangnlg.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String userRedis = (String) redisTemplate.opsForValue().get(username);
        if(StringUtils.isEmpty(userRedis)){
            Optional<UserEntity> optionalUserEntity = userRepository.findByUserName(username);
            if(optionalUserEntity.isPresent()){
                UserEntity userEntity = optionalUserEntity.get();
                redisTemplate.opsForValue().set(username, userEntity);
                return new UserDetailsModel(userEntity);
            }else {
                throw new UsernameNotFoundException(username + "not found");
            }
        }else {
            UserEntity userEntity = null;
            try {
                userEntity = objectMapper.readValue(userRedis, UserEntity.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return new UserDetailsModel(userEntity);
        }

    }
}
