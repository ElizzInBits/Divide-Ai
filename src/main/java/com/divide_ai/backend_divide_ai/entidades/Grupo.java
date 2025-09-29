package com.divide_ai.backend_divide_ai.entidades;

import java.util.ArrayList;
import java.util.List;

public class Grupo {
    private String nome;
    private List<Usuario> membros;
    private List<Despesa> despesas;
    
    public Grupo() {
        this.membros = new ArrayList<>();
        this.despesas = new ArrayList<>();
    }
    
    public Grupo(Long id, String nome, String descricao) {
        this();
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
    
    public List<Usuario> getMembros() {
        return membros;
    }
    
    public List<Despesa> getDespesas() {
        return despesas;
    }
}