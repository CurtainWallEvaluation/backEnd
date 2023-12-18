package com.mqa.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "mqa.jwt")
public class JwtProperties {
    private String secretKey;
    private Long duration;
    private String tokenName;
}
