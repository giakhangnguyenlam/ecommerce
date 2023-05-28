package com.khangnlg.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ecommerce.jwt")
@Data
public class SecurityProperty {

    private String secretKey;

    private long expirationInMinute;

    private String iss;

    private boolean enable;

}
