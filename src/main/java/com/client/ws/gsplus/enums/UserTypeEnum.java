package com.client.ws.gsplus.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserTypeEnum {
    PROFESSOR(1L),
    ADMINISTRADOR(2L),
    ALUNO(3L);

    private Long id;

}
