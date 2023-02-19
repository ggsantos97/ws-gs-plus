package com.client.ws.gsplus.service;

import com.client.ws.gsplus.model.jpa.UserCredentials;

public interface UserDetailService {

    UserCredentials loadUserByUsernameAndPass(String username, String pass);

    Object sendRecoveryCode(String email);
}
