package com.client.ws.gsplus.integration.config;

import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@NoArgsConstructor
public class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {

    //se estivesse usando um getway, aqui passaria o client_id e o secret...
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String credential = "rasmooplus:r@sm00";
        String base64 = new String (Base64.encodeBase64(credential.getBytes()));
        request.getHeaders().set("Authorization", "Basic "+base64);
        request.getHeaders().set("content-type", MediaType.APPLICATION_JSON_VALUE);

        return execution.execute(request, body);
    }

}
