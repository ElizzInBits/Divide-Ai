package com.divide_ai.backend_divide_ai.negocio;

import com.divide_ai.backend_divide_ai.entidades.Grupo;
import com.divide_ai.backend_divide_ai.entidades.Usuario;
import com.divide_ai.backend_divide_ai.repository.GrupoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;

@Service
public class GrupoService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final List<Grupo> grupos = new ArrayList<>();
    private static Long nextId = 3L;

    private final GrupoRepository grupoRepository;

    public GrupoService(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }
    
    @PostConstruct
    public void init() {
        if (grupos.isEmpty()) {
            Usuario joao = new Usuario(1L, "João Silva", "joao@email.com", "11999999999");
            Usuario maria = new Usuario(2L, "Maria Santos", "maria@email.com", "11888888888");
            Usuario pedro = new Usuario(3L, "Pedro Costa", "pedro@email.com", "11777777777");
            Usuario ana = new Usuario(4L, "Ana Oliveira", "ana@email.com", "11666666666");
            
            Grupo viagemPraia = new Grupo(1L, "Viagem Praia", "Gastos da viagem para a praia");
            viagemPraia.getMembros().add(joao);
            viagemPraia.getMembros().add(maria);
            
            Grupo churrascoAmigos = new Grupo(2L, "Churrasco Amigos", "Churrasco de fim de semana");
            churrascoAmigos.getMembros().add(pedro);
            churrascoAmigos.getMembros().add(ana);
            churrascoAmigos.getMembros().add(joao);
            
            grupos.add(viagemPraia);
            grupos.add(churrascoAmigos);
        }
    }

    public List<Grupo> listarGrupos() {
        logger.info("Listando todos os grupos");
        return new ArrayList<>(grupos);
    }

    public Grupo getGrupoById(Long id) {
        logger.info("Buscando grupo com o ID {}", id);
        return grupos.stream()
            .filter(g -> g.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Grupo com o id " + id + " não encontrado"));
    }

    public void salvarGrupo(Grupo grupo) {
        logger.info("Salvando grupo com ID {}", grupo.getId());
        if (grupo.getId() == null) {
            grupo.setId(nextId++);
        }
        grupos.removeIf(g -> g.getId().equals(grupo.getId()));
        grupos.add(grupo);
    }

    public void deletarGrupo(Long id) {
        logger.info("Deletando grupo com ID {}", id);
        grupos.removeIf(g -> g.getId().equals(id));
    }
}