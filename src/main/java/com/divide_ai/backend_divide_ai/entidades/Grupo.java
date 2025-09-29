package com.divide_ai.backend_divide_ai.entidades;

import java.util.ArrayList;
import java.util.List;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Grupo {
    private Long id;
    private String nome;
    private List<Usuario> membros;
    private List<Despesa> despesas;
    
    public Grupo() {
        this.membros = new ArrayList<>();
        this.despesas = new ArrayList<>();
    }
    
    public Grupo(Long id, String nome, String descricao) {
        this();
        this.id = id;
        this.nome = nome;
    }
    
    @DynamoDbPartitionKey
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public List<Usuario> getMembros() {
        return membros;
    }
    
    public List<Despesa> getDespesas() {
        return despesas;
    }
    
    public void setMembros(List<Usuario> membros) {
        this.membros = membros;
    }
    
    public void setDespesas(List<Despesa> despesas) {
        this.despesas = despesas;
    }
}