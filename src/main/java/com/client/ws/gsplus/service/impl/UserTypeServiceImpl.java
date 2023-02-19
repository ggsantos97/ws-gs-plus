package com.client.ws.gsplus.service.impl;

import com.client.ws.gsplus.model.jpa.UserType;
import com.client.ws.gsplus.repository.UserTypeRepository;
import com.client.ws.gsplus.service.UserTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTypeServiceImpl implements UserTypeService {
    private final UserTypeRepository userTypeRepository;

    @Override
    public List<UserType> findAll() {
        return userTypeRepository.findAll();
    }
}
