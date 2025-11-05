package com.divide_ai.backend_divide_ai.rest;

import com.divide_ai.backend_divide_ai.entidades.Despesa;
import com.divide_ai.backend_divide_ai.negocio.DespesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/despesas")
@Tag(name = "Despesas", description = "Operações relacionadas às despesas")
public class DespesaController {

    private final DespesaService despesaService;

    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    @GetMapping
    @Operation(summary = "Listar todas as despesas")
    public List<Despesa> listarDespesas() {
        return despesaService.listarDespesas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar despesa por ID")
    public Despesa buscarPorId(@PathVariable Long id) {
        return despesaService.buscarPorId(id);
    }

    @PostMapping
    @Operation(summary = "Cadastrar nova despesa")
    public void salvarDespesa(@RequestBody Despesa despesa) {
        despesaService.salvarDespesa(despesa);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir despesa")
    public void deletarDespesa(@PathVariable Long id) {
        despesaService.deletarDespesa(id);
    }
}
