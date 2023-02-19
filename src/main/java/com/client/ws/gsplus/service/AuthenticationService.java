package com.client.ws.gsplus.service;

import com.client.ws.gsplus.dto.LoginDto;
import com.client.ws.gsplus.dto.TokenDto;

public interface AuthenticationService {

    TokenDto auth(LoginDto dto);
}
