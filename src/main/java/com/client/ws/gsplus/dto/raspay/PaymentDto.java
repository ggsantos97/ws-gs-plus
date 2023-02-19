package com.client.ws.gsplus.dto.raspay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {

    private CreditCardDto creditCard;

    private String customerId;

    private String orderId;
}
