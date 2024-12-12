package com.api.controle_estacionamento.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UsuarioResponseDto {

    private UUID id;
    private String username;
    private Integer tpUsuario;

}
