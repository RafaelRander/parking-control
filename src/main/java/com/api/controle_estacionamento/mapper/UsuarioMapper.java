package com.api.controle_estacionamento.mapper;

import com.api.controle_estacionamento.dto.UsuarioDto;
import com.api.controle_estacionamento.dto.UsuarioResponseDto;
import com.api.controle_estacionamento.entity.UsuarioEntity;
import org.modelmapper.ModelMapper;

public class UsuarioMapper {
    public static UsuarioEntity toUsuarioEntity(UsuarioDto usuarioDto){
        return new ModelMapper().map(usuarioDto, UsuarioEntity.class);
    }

    public static UsuarioResponseDto toDto(UsuarioEntity usuarioEntity){
        return new ModelMapper().map(usuarioEntity, UsuarioResponseDto.class);
    }

}
