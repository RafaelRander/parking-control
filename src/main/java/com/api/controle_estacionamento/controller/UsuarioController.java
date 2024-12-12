package com.api.controle_estacionamento.controller;

import com.api.controle_estacionamento.dto.UsuarioDto;
import com.api.controle_estacionamento.dto.UsuarioResponseDto;
import com.api.controle_estacionamento.dto.UsuarioSenhaDto;
import com.api.controle_estacionamento.entity.UsuarioEntity;
import com.api.controle_estacionamento.mapper.UsuarioMapper;
import com.api.controle_estacionamento.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioDto userDto){
        UsuarioEntity user = usuarioService.salvar(UsuarioMapper.toUsuarioEntity(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> buscaPorId(@PathVariable UUID id) {
        UsuarioEntity user = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizarSenha(@PathVariable UUID id, @Valid @RequestBody UsuarioSenhaDto userDto) {
        UsuarioEntity user = usuarioService.atualizarSenha(id, userDto.getSenhaAtual(), userDto.getNovaSenha(), userDto.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> buscarTodos(){
        List<UsuarioEntity> listaUsuarios = usuarioService.buscarTodos();
        return ResponseEntity.ok(listaUsuarios);
    }

}
