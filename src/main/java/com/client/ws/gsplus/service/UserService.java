package com.client.ws.gsplus.service;

import com.client.ws.gsplus.dto.UserDto;
import com.client.ws.gsplus.model.jpa.User;

public interface UserService {
    User create(UserDto dto);
    User findByIdOrElseThrow(Long id);
}
