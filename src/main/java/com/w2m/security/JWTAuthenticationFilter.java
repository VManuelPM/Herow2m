package com.w2m.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.w2m.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter() {}

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      UserEntity credentials =
          new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);

      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              credentials.getUsername(), credentials.getPassword(), new ArrayList<>()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication auth) {

    String token = createToken(((User) auth.getPrincipal()).getUsername());

    response.addHeader(
        SecurityConstants.HEADER_AUTHORIZACION_KEY,
        SecurityConstants.TOKEN_BEARER_PREFIX + " " + token);
  }

  public String createToken(String username) {
    return Jwts.builder()
        .setIssuedAt(new Date())
        .setIssuer(SecurityConstants.ISSUER_INFO)
        .setSubject(username)
        .setExpiration(
            new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, SecurityConstants.SUPER_SECRET_KEY)
        .compact();
  }
}
