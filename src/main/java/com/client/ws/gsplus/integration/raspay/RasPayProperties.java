package com.client.ws.gsplus.integration.raspay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@NoArgsConstructor
@Getter
@Configuration
public class RasPayProperties {

    @Value("${webservices.raspay.v1.customer}")
    private String customerUrl;
    @Value("${webservices.raspay.v1.order}")
    private String orderUrl;
    @Value("${webservices.raspay.v1.payment}")
    private String paymentUrl;
}
