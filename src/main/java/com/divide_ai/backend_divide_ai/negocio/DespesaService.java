package com.divide_ai.backend_divide_ai.negocio;

import com.divide_ai.backend_divide_ai.entidades.Despesa;
import com.divide_ai.backend_divide_ai.repository.DespesaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

@Service
public class DespesaService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final DespesaRepository despesaRepository;

    public DespesaService(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }

    public List<Despesa> listarDespesas() {
        logger.info("Listando todas as despesas");
        return new ArrayList<>(); // TODO: Implementar leitura real no DynamoDB
    }

    public Despesa buscarPorId(Long id) {
        logger.info("Buscando despesa com ID {}", id);
        Despesa despesa = despesaRepository.buscarPorId(id);
        if (despesa == null) {
            throw new RuntimeException("Despesa com id " + id + " não encontrada");
        }
        return despesa;
    }

    public void salvarDespesa(Despesa despesa) {
        logger.info("Salvando despesa com ID {}", despesa.getId());
        despesaRepository.salvar(despesa);
    }

    public void deletarDespesa(Long id) {
        logger.info("Deletando despesa com ID {}", id);
        despesaRepository.deletar(id);
    }
}

