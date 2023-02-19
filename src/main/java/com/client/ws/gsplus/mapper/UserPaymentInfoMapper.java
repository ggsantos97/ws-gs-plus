package com.client.ws.gsplus.mapper;

import com.client.ws.gsplus.dto.UserPaymentInfoDto;
import com.client.ws.gsplus.model.jpa.User;
import com.client.ws.gsplus.model.jpa.UserPaymentInfo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserPaymentInfoMapper {

    public static UserPaymentInfo mapFrom(UserPaymentInfoDto dto, User user){
        return UserPaymentInfo.builder()
                .id(dto.getId())
                .cardNumber(dto.getCardNumber())
                .cardExpirationMonth(dto.getCardExpirationMonth())
                .cardExpirationYear(dto.getCardExpirationYear())
                .cardSecurityCode(dto.getCardSecurityCode())
                .price(dto.getPrice())
                .datePayment(dto.getDtPayment())
                .installments(dto.getInstallments())
                .user(user)
                .build();

    }
}
