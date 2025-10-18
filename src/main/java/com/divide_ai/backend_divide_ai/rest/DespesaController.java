package com.divide_ai.backend_divide_ai.rest;

import com.divide_ai.backend_divide_ai.entidades.Despesa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/Despesa")
@Tag(name = "Despesas")
public class DespesaController {

    private final List<Despesa> despesas = new ArrayList<>();

    @GetMapping
    @Operation(summary = "Listar Despesas cadastradas", description = "Retorna uma lista de desspesas cadastrados")
    public List<Despesa> listarDespesas() {
        return despesas;
    }

    @PostMapping
    @Operation(summary = "Cadastrar nova despesa", description = "Cria nova despesa no sistema")
    public Despesa criarDespesa(@RequestBody Despesa despesa) {
        despesas.add(despesa);
        return despesa;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir Despesa", description = "Remove uma despesa pelo ID")
    public void excluirDespesa(@PathVariable Long id) {
        despesas.removeIf(u -> Objects.equals(u.getId(), id));
    }
}
