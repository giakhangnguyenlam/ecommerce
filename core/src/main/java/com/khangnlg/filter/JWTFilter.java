package com.khangnlg.filter;

import com.auth0.jwt.JWTVerifier;
import com.khangnlg.security.JWTService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = parseJWT(request);
            if(jwt != null){
                UserDetails user = jwtService.validateUserToken(jwt);
                if(Objects.isNull(user)){
                    throw new UsernameNotFoundException("user not found");
                }
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationFilter =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                usernamePasswordAuthenticationFilter.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationFilter);
            }

            filterChain.doFilter(request, response);

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private String parseJWT(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        if(!StringUtils.isEmpty(authorization) && authorization.startsWith("Bearer")){
            return authorization.split(" ")[1];
        }
        return  null;


    }
}
