package com.client.ws.gsplus.integration.raspay;

import com.client.ws.gsplus.dto.raspay.CustomerDto;
import com.client.ws.gsplus.dto.raspay.OrderDto;
import com.client.ws.gsplus.dto.raspay.PaymentDto;
import com.client.ws.gsplus.exception.BadRequestException;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class RasPayIntegration {


    private  RestTemplate restTemplateGetwayService;
    private RasPayProperties rasPayProperties;


    public CustomerDto createCustomer(CustomerDto dto) {
        try {
            HttpEntity<CustomerDto> request = new HttpEntity<>(dto);
            ResponseEntity<CustomerDto> response =
                    restTemplateGetwayService.exchange(rasPayProperties.getCustomerUrl(),
                            HttpMethod.POST,
                            request,
                            CustomerDto.class);
            return response.getBody();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }


    public OrderDto createOrder(OrderDto dto) {
        try {
            HttpEntity<OrderDto> request = new HttpEntity<>(dto);
            ResponseEntity<OrderDto> response =
                    restTemplateGetwayService.exchange(rasPayProperties.getOrderUrl(),
                            HttpMethod.POST,
                            request,
                            OrderDto.class);
            return response.getBody();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Boolean processPayment(PaymentDto dto) {
        try {
            HttpEntity<PaymentDto> request = new HttpEntity<>(dto);
            ResponseEntity<Boolean> response =
                    restTemplateGetwayService.exchange(rasPayProperties.getPaymentUrl(),
                            HttpMethod.POST,
                            request,
                            Boolean.class);
            return response.getBody();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }



    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String credential = "rasmooplus:r@sm00";
        String base64 = new String (Base64.encodeBase64(credential.getBytes()));
        headers.add("Authorization","Basic "+base64);
        return headers;
    }
}
