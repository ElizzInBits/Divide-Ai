package com.divide_ai.backend_divide_ai.negocio;

import com.divide_ai.backend_divide_ai.entidades.Usuario;
import com.divide_ai.backend_divide_ai.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {
        logger.info("Listando todos os usuários");
        return new ArrayList<>(); // TODO: Implementar leitura real no DynamoDB
    }

    public Usuario buscarPorId(Long id) {
        logger.info("Buscando usuário com ID {}", id);
        Usuario usuario = usuarioRepository.buscarPorId(id);
        if (usuario == null) {
            throw new RuntimeException("Usuário com id " + id + " não encontrado");
        }
        return usuario;
    }

    public void salvarUsuario(Usuario usuario) {
        logger.info("Salvando usuário com ID {}", usuario.getId());
        usuarioRepository.salvar(usuario);
    }

    public void deletarUsuario(Long id) {
        logger.info("Deletando usuário com ID {}", id);
        usuarioRepository.deletar(id);
    }
}
