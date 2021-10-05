package com.w2m;

import com.w2m.security.SecurityConstants;
import com.w2m.utils.ApplicationConstants;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(
    info = @Info(title = ApplicationConstants.API_TITLE, version = ApplicationConstants.VERSION))
@SecurityScheme(
        name = SecurityConstants.SWAGGER_BEARER_NAME,
        type = SecuritySchemeType.HTTP,
        bearerFormat = SecurityConstants.SWAGGER_BEARER_FORMAT,
        scheme = SecurityConstants.SWAGGER_SCHEMA,
        in = SecuritySchemeIn.HEADER
)
public class W2mApplication {
  public static void main(String[] args) {
    SpringApplication.run(W2mApplication.class, args);
  }
}
