package com.divide_ai.backend_divide_ai.negocio;

import com.divide_ai.backend_divide_ai.entidades.Usuario;
import com.divide_ai.backend_divide_ai.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;

@Service
public class UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static Long nextId = 5L;

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    @PostConstruct
    public void init() {
        if (usuarios.isEmpty()) {
            usuarios.add(new Usuario(1L, "João Silva", "joao@email.com", "11999999999"));
            usuarios.add(new Usuario(2L, "Maria Santos", "maria@email.com", "11888888888"));
            usuarios.add(new Usuario(3L, "Pedro Costa", "pedro@email.com", "11777777777"));
            usuarios.add(new Usuario(4L, "Ana Oliveira", "ana@email.com", "11666666666"));
        }
    }

    public List<Usuario> listarUsuarios() {
        logger.info("Listando todos os usuários");
        return new ArrayList<>(usuarios);
    }

    public Usuario buscarPorId(Long id) {
        logger.info("Buscando usuário com ID {}", id);
        return usuarios.stream()
            .filter(u -> u.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Usuário com id " + id + " não encontrado"));
    }

    public void salvarUsuario(Usuario usuario) {
        logger.info("Salvando usuário com ID {}", usuario.getId());
        if (usuario.getId() == null) {
            usuario.setId(nextId++);
        }
        usuarios.removeIf(u -> u.getId().equals(usuario.getId()));
        usuarios.add(usuario);
    }

    public void deletarUsuario(Long id) {
        logger.info("Deletando usuário com ID {}", id);
        usuarios.removeIf(u -> u.getId().equals(id));
    }
}
