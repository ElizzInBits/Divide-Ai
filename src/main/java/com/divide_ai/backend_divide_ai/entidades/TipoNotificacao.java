package com.divide_ai.backend_divide_ai.entidades;

public enum TipoNotificacao {
    NOVA_DESPESA("Nova despesa adicionada"),
    SALDO_ATUALIZADO("Saldo atualizado"),
    LEMBRETE_PAGAMENTO("Lembrete de pagamento"),
    NOVO_MEMBRO("Novo membro no grupo"),
    DESPESA_EDITADA("Despesa editada");
    
    private final String descricao;
    
    TipoNotificacao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
}