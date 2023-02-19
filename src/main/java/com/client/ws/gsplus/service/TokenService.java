package com.client.ws.gsplus.service;

public interface TokenService {
    String getToken(Long userId);

    Boolean isValid(String token);

    Long getUserId(String token);
}
