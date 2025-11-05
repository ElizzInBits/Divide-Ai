package com.divide_ai.backend_divide_ai.negocio;

import com.divide_ai.backend_divide_ai.entidades.Grupo;
import com.divide_ai.backend_divide_ai.repository.GrupoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

@Service
public class GrupoService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final GrupoRepository grupoRepository;

    public GrupoService(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    public List<Grupo> listarGrupos() {
        logger.info("Listando todos os grupos");

        // DynamoDB não tem findAll padrão — então você pode manter um mock por enquanto:
        List<Grupo> grupos = new ArrayList<>();
        // Exemplo: grupos.add(new Grupo(1L, "Grupo exemplo"));
        return grupos;
    }

    public Grupo getGrupoById(Long id) {
        logger.info("Buscando grupo com o ID {}", id);

        Grupo grupo = grupoRepository.buscarPorId(id);
        if (grupo == null) {
            throw new RuntimeException("Grupo com o id " + id + " não encontrado");
        }
        return grupo;
    }

    public void salvarGrupo(Grupo grupo) {
        logger.info("Salvando grupo com ID {}", grupo.getId());
        grupoRepository.salvar(grupo);
    }

    public void deletarGrupo(Long id) {
        logger.info("Deletando grupo com ID {}", id);
        grupoRepository.deletar(id);
    }
}
