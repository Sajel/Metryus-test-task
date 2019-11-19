package com.testtask.usermanagementapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testtask.usermanagementapi.dto.LoginDTO;
import com.testtask.usermanagementapi.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.testtask.usermanagementapi.security.SecurityConstants.*;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper objectMapper;
    private AuthenticationManager authenticationManager;

    protected LoginFilter(ObjectMapper objectMapper, AuthenticationManager authenticationManager) {
        super("/api/login");
        this.objectMapper = objectMapper;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {
        LoginDTO loginDTO = objectMapper.readValue(request.getInputStream(), LoginDTO.class);
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getLogin(),
                loginDTO.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        User user = (User) auth.getPrincipal();
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", objectMapper.writeValueAsString(user.getRoles()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        response.addHeader(TOKEN_HEADER_NAME, TOKEN_PREFIX + token);
    }
}
