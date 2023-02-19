package com.client.ws.gsplus.mapper;

import com.client.ws.gsplus.dto.SubscriptionTypeDto;
import com.client.ws.gsplus.model.jpa.SubscriptionType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubscriptionTypeMapper {

    public static SubscriptionType mapFrom(SubscriptionTypeDto dto) {
        return SubscriptionType.builder()
                .id(dto.getId())
                .name(dto.getName())
                .accessMonths(dto.getAccessMonths())
                .price(dto.getPrice())
                .productKey(dto.getProductKey())
                .build();
    }
}
