package com.divide_ai.backend_divide_ai.rest;

import com.divide_ai.backend_divide_ai.entidades.Grupo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/Grupo")
@Tag(name = "Grupos")
public class GrupoController {

    private final List<Grupo> grupos = new ArrayList<>();

    @GetMapping
    @Operation(summary = "Listar todos os grupos registrados", description = "Retorna uma lista de grupos cadastrados")
    public List<Grupo> listarGrupos() {
        return grupos;
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo grupo", description = "Cria um novo grupo no sistema")
    public Grupo criarGrupo(@RequestBody Grupo grupo) {
        grupos.add(grupo);
        return grupo;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir grupo", description = "Remove um grupo pelo ID")
    public void excluirGrupo(@PathVariable Long id) {
        grupos.removeIf(u -> Objects.equals(u.getId(), id));
    }
}
