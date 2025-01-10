package com.api.controle_estacionamento.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
@Table(name="usuario")
public class UsuarioEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private UUID id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    //username será hash do email cadastrado do usuário.
    @Column(name = "username", nullable = false, unique = true, length = 200)
    private String username;

    @Column(name = "password", nullable = false, length = 10)
    private String password;

    @Column(name = "email", nullable = false, unique = true, length = 250)
    private String email;

    //tp_usuario = 1 == usuario comum    tp_usuario = 2 == usuario admin
    @Column(name = "tp_usuario", nullable = false)
    private Integer tpUsuario;

    @Column(name = "ds_usu_criacao", nullable = false, length = 100)
    private String dsUsuCriacao;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "ds_usu_modificacao", nullable = false, length = 100)
    private String dsUsuModificacao;

    @Column(name = "data_modificacao", nullable = false)
    private LocalDateTime dataModificacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UsuarioEntity{" +
                "id=" + id +
                '}';
    }

}
