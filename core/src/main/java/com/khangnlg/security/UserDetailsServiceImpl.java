package com.khangnlg.security;

import com.khangnlg.entities.UserEntity;
import com.khangnlg.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUserName(username);
        if(optionalUserEntity.isPresent()){
            return new UserDetailsModel(optionalUserEntity.get());
        }else {
            throw new UsernameNotFoundException(username + "not found");
        }
    }
}
