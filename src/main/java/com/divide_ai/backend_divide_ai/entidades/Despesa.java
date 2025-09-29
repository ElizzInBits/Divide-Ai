package com.divide_ai.backend_divide_ai.entidades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Despesa {
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
        this.descricao = descricao;
        this.valor = valor;
        this.pagador = pagador;
    }
    
    public String getDescricao() {
        return descricao;
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
}