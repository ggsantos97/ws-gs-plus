package com.client.ws.gsplus.service.impl;

import com.client.ws.gsplus.dto.SubscriptionTypeDto;
import com.client.ws.gsplus.exception.NotFoundException;
import com.client.ws.gsplus.mapper.SubscriptionTypeMapper;
import com.client.ws.gsplus.model.jpa.SubscriptionType;
import com.client.ws.gsplus.repository.SubscriptionTypeRepository;
import com.client.ws.gsplus.service.SubscriptionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {
    private final SubscriptionTypeRepository subscriptionTypeRepository;

    @Override
    //@Cacheable(value = "subscriptionType")
    public List<SubscriptionType> findAll() {
        return subscriptionTypeRepository.findAll();
    }

    @Override
    @Cacheable(value = "subscriptionType", key = "#id")
    public SubscriptionType findByIdOrElseThrow(Long id) {
        return subscriptionTypeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(String.format("Subscription not found for id: %s", id)));
    }

    @Override
    @CacheEvict(value = "subscriptionType", allEntries = true)
    public SubscriptionType create(SubscriptionTypeDto subscriptionType) {
        return subscriptionTypeRepository.save(SubscriptionTypeMapper.mapFrom(subscriptionType));
    }

    @Override
    @CacheEvict(value = "subscriptionType", allEntries = true)
    public SubscriptionType update(Long id, SubscriptionTypeDto subscriptionType) {
        findByIdOrElseThrow(id);
        subscriptionType.setId(id);
        return subscriptionTypeRepository.save(SubscriptionTypeMapper.mapFrom(subscriptionType));
    }

    @Override
    @CacheEvict(value = "subscriptionType", allEntries = true)
    public void delete(Long id) {
        findByIdOrElseThrow(id);
        subscriptionTypeRepository.deleteById(id);
    }
}
