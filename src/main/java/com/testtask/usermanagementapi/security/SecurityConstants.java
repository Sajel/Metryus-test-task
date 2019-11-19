package com.testtask.usermanagementapi.security;

public interface SecurityConstants {
    String SECRET = "SecretKeyToGenJWTs";
    long EXPIRATION_TIME = 864_000_000; // 10 days
    String TOKEN_PREFIX = "Bearer ";
    String TOKEN_HEADER_NAME = "Authorization";

    String ADMIN_ROLE = "ADMIN";
}
