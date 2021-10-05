package com.w2m.security;

public class SecurityConstants {
  // Spring Security
  public static final String LOGIN_URL = "/login";
  public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
  public static final String TOKEN_BEARER_PREFIX = "Bearer ";

  // JWT
  public static final String ISSUER_INFO = "VManuelPM";
  public static final String SUPER_SECRET_KEY = "1234";
  public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day

  // H2
  public static final String H2_URL = "/h2-console/**";

  // SWAGGER
  public static final String[] AUTH_WHITELIST = {
    // -- swagger ui
    "/v2/api-docs/**",
    "/v3/api-docs/**",
    "/swagger-resources/**",
    "/swagger-ui/**",
    "/swagger-ui.html"
  };

  public static final String SWAGGER_BEARER_NAME = "bearerAuth";
  public static final String SWAGGER_BEARER_FORMAT = "JWT";
  public static final String SWAGGER_SCHEMA = "bearer";
}
