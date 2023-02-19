package com.client.ws.gsplus.service.impl;

import com.client.ws.gsplus.exception.BadRequestException;
import com.client.ws.gsplus.exception.NotFoundException;
import com.client.ws.gsplus.model.jpa.UserCredentials;
import com.client.ws.gsplus.repository.UserDetailsRepository;
import com.client.ws.gsplus.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailService {
    private final UserDetailsRepository userDetailsRepository;

    @Override
    public UserCredentials loadUserByUsernameAndPass(String username, String pass) {

        var userCredentials = userDetailsRepository.findByUsername(username)
                .orElseThrow(()->new NotFoundException("Usuário não encontrado"));


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (encoder.matches(pass, userCredentials.getPassword())) {
            return userCredentials;
        }

        throw new BadRequestException("Usuário ou senha inválido");
    }

    @Override
    public Object sendRecoveryCode(String email) {

//        UserRecoveryCode userRecoveryCode;
//        String code = String.format("%04d", new Random().nextInt(10000));
//        var userRecoveryCodeOpt = userRecoveryCodeRepository.findByEmail(email);
//
//        if (userRecoveryCodeOpt.isEmpty()) {
//
//            var user = userDetailsRepository.findByUsername(email);
//            if (user.isEmpty()) {
//                throw new NotFoudException("Usuário não encontrado");
//            }
//
//            userRecoveryCode = new UserRecoveryCode();
//            userRecoveryCode.setEmail(email);
//
//        } else {
//            userRecoveryCode = userRecoveryCodeOpt.get();
//        }
//        userRecoveryCode.setCode(code);
//        userRecoveryCode.setCreationDate(LocalDateTime.now());
//
//        userRecoveryCodeRepository.save(userRecoveryCode);
//        mailIntegration.send(email, "Código de recuperação de conta: "+code, "Código de recuperação de conta");
        return null;
    }
}
