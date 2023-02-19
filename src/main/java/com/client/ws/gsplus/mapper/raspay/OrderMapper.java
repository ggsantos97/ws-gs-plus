package com.client.ws.gsplus.mapper.raspay;

import com.client.ws.gsplus.dto.PaymentProcessDto;
import com.client.ws.gsplus.dto.raspay.OrderDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderMapper {

    public static OrderDto mapFrom(String customerId, PaymentProcessDto paymentProcessDto) {
        return OrderDto.builder()
                .customerId(customerId)
                .productAcronym(paymentProcessDto.getProductKey())
                .discount(paymentProcessDto.getDiscount())
                .build();
    }
}
