package com.api.controle_estacionamento.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UsuarioDto {

    @NotBlank
    private String userName;

    @Size(min=6, max=6)
    private String password;

    @NotBlank
    @Email(message="Formato do e-mail está inválido.", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String email;

    @NotNull
    private Integer tpUsuario;

    private String dsUsuCriacao;

    private LocalDateTime dataCriacao;

    private String dsUsuModificacao;

    private LocalDateTime dataModificacao;


}
