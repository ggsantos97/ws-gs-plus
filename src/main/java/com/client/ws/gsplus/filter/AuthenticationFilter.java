package com.client.ws.gsplus.filter;

import com.client.ws.gsplus.exception.NotFoundException;
import com.client.ws.gsplus.model.jpa.UserCredentials;
import com.client.ws.gsplus.repository.UserDetailsRepository;
import com.client.ws.gsplus.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
public class AuthenticationFilter  extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserDetailsRepository userDetailsRepository;
    @Override
    protected void doFilterInternal(
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {
        String token = getBearerToken(request);
        if (tokenService.isValid(token)) {
            authByToken(token);
        }
        filterChain.doFilter(request, response);
    }

    private String getBearerToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        if (Objects.isNull(token) || !token.startsWith("Bearer")) {
            return null;
        }

        return token.substring(7, token.length());
    }
    private void authByToken(String token) {

        //recuperar id do usuario
        Long userId = tokenService.getUserId(token);
        var user = userDetailsRepository.findById(userId)
                .orElseThrow(()-> new NotFoundException("Usuário não encontrado"));

        //autenticar no spring

        UsernamePasswordAuthenticationToken userAuth
                = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(userAuth);
    }
}
