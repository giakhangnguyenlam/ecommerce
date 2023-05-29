package com.khangnlg.security;

import com.khangnlg.entities.UserEntity;
import com.khangnlg.repositories.UserRepository;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = (UserEntity) redisTemplate.opsForValue().get(username);
        if(userEntity == null){
            Optional<UserEntity> optionalUserEntity = userRepository.findByUserName(username);
            if(optionalUserEntity.isPresent()){
                userEntity = optionalUserEntity.get();
                redisTemplate.opsForValue().set(username, userEntity);
                return new UserDetailsModel(userEntity);
            }else {
                throw new UsernameNotFoundException(username + "not found");
            }
        }else {
            return new UserDetailsModel(userEntity);
        }

    }
}
