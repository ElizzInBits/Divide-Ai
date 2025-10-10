package com.divide_ai.backend_divide_ai.entidades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Despesa {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private Usuario pagador;
    private List<Usuario> participantes;
    private TipoDivisao tipoDivisao;
    
    public Despesa() {
        this.participantes = new ArrayList<>();
        this.tipoDivisao = TipoDivisao.IGUAL;
    }
    
    public Despesa(Long id, String descricao, BigDecimal valor, Usuario pagador, Grupo grupo) {
        this();
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.pagador = pagador;
    }
    
    @DynamoDbPartitionKey
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public BigDecimal getValor() {
        return valor;
    }
    
    public Usuario getPagador() {
        return pagador;
    }
    
    public List<Usuario> getParticipantes() {
        return participantes;
    }
    
    public TipoDivisao getTipoDivisao() {
        return tipoDivisao;
    }
    
    public void setTipoDivisao(TipoDivisao tipoDivisao) {
        this.tipoDivisao = tipoDivisao;
    }
    
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
    public void setPagador(Usuario pagador) {
        this.pagador = pagador;
    }
    
    public void setParticipantes(List<Usuario> participantes) {
        this.participantes = participantes;
    }
}