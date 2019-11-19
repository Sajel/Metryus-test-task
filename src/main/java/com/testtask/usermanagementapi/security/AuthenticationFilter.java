package com.testtask.usermanagementapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testtask.usermanagementapi.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static com.testtask.usermanagementapi.security.SecurityConstants.*;

public class AuthenticationFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    public AuthenticationFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtHeader = request.getHeader(TOKEN_HEADER_NAME);
        if (jwtHeader != null && jwtHeader.startsWith(TOKEN_PREFIX)) {
            String jwt = jwtHeader.substring(SecurityConstants.TOKEN_PREFIX.length());
            parseJwt(jwt);
        }
        chain.doFilter(request, response);
    }

    private void parseJwt(String jwt) throws com.fasterxml.jackson.core.JsonProcessingException {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(jwt);
        String subject = claims.getBody().getSubject();
        if (subject != null) {
            String rolesJson = claims.getBody().get("roles", String.class);
            Collection<Role> roles = Arrays.asList(objectMapper.readValue(rolesJson, Role[].class));
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(subject, null, roles));
        }
    }
}
