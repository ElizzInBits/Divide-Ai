package com.divide_ai.backend_divide_ai.servicos;

import com.divide_ai.backend_divide_ai.entidades.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorNotificacoes {
    private List<Notificacao> notificacoes = new ArrayList<>();
    
    public void notificarNovaDespesa(Despesa despesa) {
        for (Usuario participante : despesa.getParticipantes()) {
            if (!participante.equals(despesa.getPagador())) {
                Notificacao notificacao = new Notificacao(
                    null,
                    "Nova Despesa",
                    String.format("%s adicionou uma despesa: %s - R$ %.2f", 
                        despesa.getPagador().getNome(),
                        despesa.getDescricao(),
                        despesa.getValor()),
                    participante,
                    TipoNotificacao.NOVA_DESPESA
                );
                notificacoes.add(notificacao);
            }
        }
    }
    
    public void notificarSaldoAtualizado(List<Saldo> saldos) {
        for (Saldo saldo : saldos) {
            Notificacao notificacao = new Notificacao(
                null,
                "Saldo Atualizado",
                String.format("Seu saldo foi atualizado. Saldo atual: R$ %.2f", 
                    saldo.getSaldoFinal()),
                saldo.getUsuario(),
                TipoNotificacao.SALDO_ATUALIZADO
            );
            notificacoes.add(notificacao);
        }
    }
    
    public void exibirNotificacoes() {
        System.out.println("\n=== NOTIFICACOES ===");
        for (Notificacao notificacao : notificacoes) {
            System.out.printf("[%s] %s: %s%n",
                notificacao.getDestinatario().getNome(),
                notificacao.getTitulo(),
                notificacao.getMensagem());
        }
    }
    

}
