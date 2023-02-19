package com.client.ws.gsplus.service.impl;

import com.client.ws.gsplus.dto.PaymentProcessDto;
import com.client.ws.gsplus.dto.raspay.CustomerDto;
import com.client.ws.gsplus.dto.raspay.OrderDto;
import com.client.ws.gsplus.dto.raspay.PaymentDto;
import com.client.ws.gsplus.enums.UserTypeEnum;
import com.client.ws.gsplus.exception.BadRequestException;
import com.client.ws.gsplus.exception.NotFoundException;
import com.client.ws.gsplus.integration.mail.MailIntegration;
import com.client.ws.gsplus.integration.raspay.RaspayIntegration;
import com.client.ws.gsplus.mapper.UserPaymentInfoMapper;
import com.client.ws.gsplus.mapper.raspay.CreditCardMapper;
import com.client.ws.gsplus.mapper.raspay.CustomerMapper;
import com.client.ws.gsplus.mapper.raspay.OrderMapper;
import com.client.ws.gsplus.mapper.raspay.PaymentMapper;
import com.client.ws.gsplus.model.jpa.User;
import com.client.ws.gsplus.model.jpa.UserCredentials;
import com.client.ws.gsplus.model.jpa.UserPaymentInfo;
import com.client.ws.gsplus.repository.*;
import com.client.ws.gsplus.service.PaymentInfoService;
import com.client.ws.gsplus.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PaymentInfoServiceImpl implements PaymentInfoService {

    @Value("${gsplus.default.password}")
    private String defaultPass;
    private final UserRepository userRepository;
    private final UserPaymentInfoRepository userPaymentInfoRepository;
    private final RaspayIntegration raspayIntegration;
    private final MailIntegration mailIntegration;
    private final UserDetailsRepository userDetailsRepository;
    private final UserTypeRepository userTypeRepository;
    private final SubscriptionTypeRepository subscriptionTypeRepository;

    @Override
    public Boolean process(PaymentProcessDto dto) {
        final var user = userRepository.findById(dto.getUserPaymentInfoDto().getUserId())
                .orElseThrow(()-> new NotFoundException("Usuário não encontrado"));
        if (Objects.nonNull(user.getSubscriptionType())) {
            //todo exception de negocio
            throw new BadRequestException("Pagamento não pode ser processado pois usuário já possui assinatura");
        }
        return createUserCredentials(dto,user, getSucessPayment(dto,user));
    }

    private Boolean getSucessPayment(PaymentProcessDto dto, User user) {
        CustomerDto customerDto = raspayIntegration.createCustomer(CustomerMapper.mapFrom(user));
        OrderDto orderDto = raspayIntegration.createOrder(OrderMapper.mapFrom(customerDto.getId(), dto));
        PaymentDto paymentDto = PaymentMapper.mapFrom(customerDto.getId(), orderDto.getId(),
                CreditCardMapper.mapFrom(dto.getUserPaymentInfoDto(), user.getCpf()));
        return raspayIntegration.processPayment(paymentDto);
    }

    private boolean createUserCredentials(PaymentProcessDto dto, User user, Boolean successPayment) {
        if (Boolean.TRUE.equals(successPayment)) {
            UserPaymentInfo userPaymentInfo = UserPaymentInfoMapper.mapFrom(dto.getUserPaymentInfoDto(), user);
            userPaymentInfoRepository.save(userPaymentInfo);

            var userType = userTypeRepository.findById(UserTypeEnum.ALUNO.getId())
                    .orElseThrow(() -> new NotFoundException("UserType não encontrado"));

            UserCredentials userCredentials = new UserCredentials(null, user.getEmail(),
                    PasswordUtils.encode(defaultPass), userType);
            userDetailsRepository.save(userCredentials);

            var subscriptionType = subscriptionTypeRepository.findByProductKey(dto.getProductKey())
                    .orElseThrow(()-> new NotFoundException("SubscriptionType não encontrado"));

            user.setSubscriptionType(subscriptionType);
            userRepository.save(user);

            mailIntegration.send(user.getEmail(), "Usuario: " + user.getEmail() + " - Senha: " + defaultPass, "Acesso liberado");
            return true;
        }
        return false;
    }
}
