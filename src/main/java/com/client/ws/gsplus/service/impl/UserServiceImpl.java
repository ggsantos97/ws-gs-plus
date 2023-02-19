package com.client.ws.gsplus.service.impl;

import com.client.ws.gsplus.dto.UserDto;
import com.client.ws.gsplus.exception.BadRequestException;
import com.client.ws.gsplus.exception.NotFoundException;
import com.client.ws.gsplus.mapper.UserMapper;
import com.client.ws.gsplus.model.jpa.User;
import com.client.ws.gsplus.repository.UserRepository;
import com.client.ws.gsplus.repository.UserTypeRepository;
import com.client.ws.gsplus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;

    @Override
    public User create(UserDto dto) {
        if (Objects.nonNull(dto.getId())) {
            throw new BadRequestException("id deve ser nulo");
        }

       final var userType=  userTypeRepository.findById(dto.getUserTypeId())
                 .orElseThrow(()-> new NotFoundException("userTypeId não encontrado"));

        User user = UserMapper.mapFrom(dto, userType, null);
        return userRepository.save(user);
    }
}
