package com.api.controle_estacionamento.service;

import com.api.controle_estacionamento.entity.UsuarioEntity;
import com.api.controle_estacionamento.exception.EntityNotFoundException;
import com.api.controle_estacionamento.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
     public UsuarioEntity salvar(UsuarioEntity usuarioEntity){
            return usuarioRepository.save(usuarioEntity);
     }

    public UsuarioEntity buscarPorId(UUID id) {
        return usuarioRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(String.format("Usuário id='%s' não encontrado.", id)));
    }

    @Transactional
    public UsuarioEntity atualizarSenha(UUID id, String senhaAtual, String novaSenha, String confirmaSenha) {
        UsuarioEntity user = buscarPorId(id);
        if(!senhaAtual.equals(user.getPassword())){
            throw new RuntimeException("Senha informada, não condiz com a senha atual do usuário!");
        }
        else if(!novaSenha.equals(confirmaSenha)){
                throw new RuntimeException("Nova senha e confirmação de senha são diferentes!");
        }
        else{
             user.setPassword(novaSenha);
        }
        return usuarioRepository.save(user);
    }

    public List<UsuarioEntity> buscarTodos() {
        return usuarioRepository.findAll();
    }
}
