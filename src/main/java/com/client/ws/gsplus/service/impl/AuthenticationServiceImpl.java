package com.client.ws.gsplus.service.impl;

import com.client.ws.gsplus.dto.LoginDto;
import com.client.ws.gsplus.dto.TokenDto;
import com.client.ws.gsplus.exception.BadRequestException;
import com.client.ws.gsplus.model.jpa.UserCredentials;
import com.client.ws.gsplus.service.AuthenticationService;
import com.client.ws.gsplus.service.TokenService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserDetailsServiceImpl userDetailsService;
    private final TokenService tokenService;

    @Override
    public TokenDto auth(LoginDto dto) {
        try {
            UserCredentials userCredentials = userDetailsService.loadUserByUsernameAndPass(dto.getUsername(), dto.getPassword());
            String token = tokenService.getToken(userCredentials.getId());
            return TokenDto.builder().token(token).type("Bearer").build();
        } catch (Exception e) {
            throw new BadRequestException("Erro ao formatar token - "+e.getMessage());
        }
    }
}
