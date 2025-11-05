package com.divide_ai.backend_divide_ai.rest;

import com.divide_ai.backend_divide_ai.entidades.Grupo;
import com.divide_ai.backend_divide_ai.negocio.GrupoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
@Tag(name = "Grupos", description = "Operações relacionadas aos grupos do sistema")
public class GrupoController {

    private final GrupoService grupoService;

    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os grupos", description = "Retorna uma lista de grupos cadastrados")
    public List<Grupo> listarGrupos() {
        return grupoService.listarGrupos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar grupo por ID", description = "Retorna o grupo correspondente ao ID informado")
    public Grupo getGrupoById(@PathVariable Long id) {
        return grupoService.getGrupoById(id);
    }

    @PostMapping
    @Operation(summary = "Criar grupo", description = "Cadastra um novo grupo no sistema")
    public void salvarGrupo(@RequestBody Grupo grupo) {
        grupoService.salvarGrupo(grupo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir grupo", description = "Remove um grupo pelo ID")
    public void deletarGrupo(@PathVariable Long id) {
        grupoService.deletarGrupo(id);
    }
}
