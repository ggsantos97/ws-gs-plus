package com.client.ws.gsplus.mapper;

import com.client.ws.gsplus.dto.UserDto;
import com.client.ws.gsplus.model.jpa.SubscriptionType;
import com.client.ws.gsplus.model.jpa.User;
import com.client.ws.gsplus.model.jpa.UserType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static User mapFrom(UserDto dto, UserType userType, SubscriptionType subscriptionType) {
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .dtSubscription(dto.getDtSubscription())
                .dtExpiration(dto.getDtExpiration())
                .userType(userType)
                .subscriptionType(subscriptionType)
                .build();
    }
}
