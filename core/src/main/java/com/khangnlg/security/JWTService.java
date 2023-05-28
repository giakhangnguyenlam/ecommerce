package com.khangnlg.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.khangnlg.models.Token;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class JWTService {

    @Autowired
    private SecurityProperty securityProperty;

    private Algorithm algorithm;

    private JWTVerifier jwtVerifier;

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @PostConstruct
    public void init() throws UnsupportedEncodingException{
        algorithm = Algorithm.HMAC256(securityProperty.getSecretKey());
        jwtVerifier = JWT.require(algorithm).withIssuer(securityProperty.getIss()).build();
    }

    public Token generateToken(String username){
        String token = JWT.create().withSubject(username)
                .withIssuer(securityProperty.getIss())
                .withExpiresAt(Date.from(LocalDateTime.now(ZoneId.systemDefault()).plusMinutes(securityProperty.getExpirationInMinute()).toInstant(ZoneOffset.UTC)))
                .sign(algorithm);

        return new Token(token);
    }

    public boolean isValidToken(String token){
        if(StringUtils.isEmpty(token)){
            return false;
        }
        try{
            jwtVerifier.verify(token);
            return true;
        }catch (JWTVerificationException e){
            return false;
        }
    }

    public UserDetails validateUserToken(String token){
        if(!securityProperty.isEnable() || token == null || !isValidToken(token)){
            return null;
        }
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return getUser(decodedJWT);
    }

    private UserDetails getUser(DecodedJWT decodedJWT) {
        String sub = decodedJWT.getSubject();
        if (sub == null) {
            return null;
        }

        return userDetailsService.loadUserByUsername(sub);
    }



}
