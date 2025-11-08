package com.divide_ai.backend_divide_ai.negocio;

import com.divide_ai.backend_divide_ai.entidades.Despesa;
import com.divide_ai.backend_divide_ai.entidades.Usuario;
import com.divide_ai.backend_divide_ai.entidades.Grupo;
import com.divide_ai.backend_divide_ai.repository.DespesaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;

@Service
public class DespesaService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final List<Despesa> despesas = new ArrayList<>();
    private static Long nextId = 4L;

    private final DespesaRepository despesaRepository;

    public DespesaService(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }
    
    @PostConstruct
    public void init() {
        if (despesas.isEmpty()) {
            Usuario joao = new Usuario(1L, "João Silva", "joao@email.com", "11999999999");
            Usuario maria = new Usuario(2L, "Maria Santos", "maria@email.com", "11888888888");
            Grupo viagemPraia = new Grupo(1L, "Viagem Praia", "Gastos da viagem para a praia");
            
            despesas.add(new Despesa(1L, "Jantar no restaurante", new BigDecimal("120.00"), joao, viagemPraia));
            despesas.add(new Despesa(2L, "Combustível", new BigDecimal("80.00"), maria, viagemPraia));
            despesas.add(new Despesa(3L, "Hotel", new BigDecimal("200.00"), joao, viagemPraia));
        }
    }

    public List<Despesa> listarDespesas() {
        logger.info("Listando todas as despesas");
        return new ArrayList<>(despesas);
    }

    public Despesa buscarPorId(Long id) {
        logger.info("Buscando despesa com ID {}", id);
        return despesas.stream()
            .filter(d -> d.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Despesa com id " + id + " não encontrada"));
    }

    public void salvarDespesa(Despesa despesa) {
        logger.info("Salvando despesa com ID {}", despesa.getId());
        if (despesa.getId() == null) {
            despesa.setId(nextId++);
        }
        despesas.removeIf(d -> d.getId().equals(despesa.getId()));
        despesas.add(despesa);
    }

    public void deletarDespesa(Long id) {
        logger.info("Deletando despesa com ID {}", id);
        despesas.removeIf(d -> d.getId().equals(id));
    }
}