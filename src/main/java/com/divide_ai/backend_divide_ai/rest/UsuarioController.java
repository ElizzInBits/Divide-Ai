package com.divide_ai.backend_divide_ai.rest;

import com.divide_ai.backend_divide_ai.entidades.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/Usuario")
@Tag(name = "Usuários")
public class UsuarioController {

    private final List<Usuario> usuarios = new ArrayList<>();

    @GetMapping
    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista de usuários cadastrados")
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo usuário", description = "Cria um novo usuário no sistema")
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir usuário", description = "Remove um usuário pelo ID")
    public void excluirUsuario(@PathVariable Long id) {
        usuarios.removeIf(u -> Objects.equals(u.getId(), id));
    }
}
