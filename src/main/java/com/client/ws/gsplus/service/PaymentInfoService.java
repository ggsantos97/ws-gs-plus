package com.client.ws.gsplus.service;

import com.client.ws.gsplus.dto.PaymentProcessDto;

public interface PaymentInfoService {

    Boolean process(PaymentProcessDto dto);
}
