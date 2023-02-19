package com.client.ws.gsplus.mapper.raspay;

import com.client.ws.gsplus.dto.raspay.CreditCardDto;
import com.client.ws.gsplus.dto.raspay.PaymentDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentMapper {

    public static PaymentDto mapFrom(String customerId, String orderId, CreditCardDto dto) {

        return PaymentDto.builder()
                .customerId(customerId)
                .orderId(orderId)
                .creditCard(dto)
                .build();

    }
}
