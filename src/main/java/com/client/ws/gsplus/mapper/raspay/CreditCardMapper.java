package com.client.ws.gsplus.mapper.raspay;

import com.client.ws.gsplus.dto.UserPaymentInfoDto;
import com.client.ws.gsplus.dto.raspay.CreditCardDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreditCardMapper {

    public static CreditCardDto mapFrom(UserPaymentInfoDto dto, String documentNumber) {
        return CreditCardDto.builder()
                .documentNumber(documentNumber)
                .cvv(Long.parseLong(dto.getCardSecurityCode()))
                .number(dto.getCardNumber())
                .month(dto.getCardExpirationMonth())
                .year(dto.getCardExpirationYear())
                .installments(dto.getInstallments())
                .build();
    }
}
