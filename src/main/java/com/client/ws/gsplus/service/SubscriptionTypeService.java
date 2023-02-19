package com.client.ws.gsplus.service;

import com.client.ws.gsplus.dto.SubscriptionTypeDto;
import com.client.ws.gsplus.model.jpa.SubscriptionType;

import java.util.List;

public interface SubscriptionTypeService {

    List<SubscriptionType> findAll();
    SubscriptionType findByIdOrElseThrow(Long id);
    SubscriptionType create(SubscriptionTypeDto subscriptionType);
    SubscriptionType update(Long id,SubscriptionTypeDto subscriptionType);
    void delete(Long id);

}
