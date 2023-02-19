package com.client.ws.gsplus.service.impl;

import com.client.ws.gsplus.dto.UserDto;
import com.client.ws.gsplus.model.jpa.User;
import com.client.ws.gsplus.repository.UserRepository;
import com.client.ws.gsplus.repository.UserTypeRepository;
import com.client.ws.gsplus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    @Override
    public User create(UserDto dto) {
        return null;
    }
}
