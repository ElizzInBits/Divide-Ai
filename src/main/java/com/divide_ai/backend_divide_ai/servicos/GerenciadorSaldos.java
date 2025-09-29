package com.divide_ai.backend_divide_ai.servicos;

import com.divide_ai.backend_divide_ai.entidades.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GerenciadorSaldos {
    private CalculadoraDivisao calculadora = new CalculadoraDivisao();
    
    public List<Saldo> calcularSaldos(Grupo grupo) {
        List<Saldo> saldos = new ArrayList<>();
        
        for (Usuario membro : grupo.getMembros()) {
            Saldo saldo = new Saldo(null, membro, grupo);
            
            // Calcula total pago pelo usuário
            BigDecimal totalPago = BigDecimal.ZERO;
            for (Despesa despesa : grupo.getDespesas()) {
                if (despesa.getPagador().equals(membro)) {
                    totalPago = totalPago.add(despesa.getValor());
                }
            }
            saldo.setTotalPago(totalPago);
            
            // Calcula total devido pelo usuário
            BigDecimal totalDevido = BigDecimal.ZERO;
            for (Despesa despesa : grupo.getDespesas()) {
                if (despesa.getParticipantes().contains(membro)) {
                    Map<Usuario, BigDecimal> divisao = calculadora.calcularDivisao(despesa);
                    BigDecimal valorDevido = divisao.get(membro);
                    if (valorDevido != null) {
                        totalDevido = totalDevido.add(valorDevido);
                    }
                }
            }
            saldo.setTotalDevido(totalDevido);
            
            // Calcula saldo final
            saldo.calcularSaldo();
            saldos.add(saldo);
        }
        
        return saldos;
    }
    
    public void exibirSaldos(List<Saldo> saldos) {
        System.out.println("\n=== SALDOS DO GRUPO ===");
        for (Saldo saldo : saldos) {
            System.out.printf("%s: Pagou R$ %.2f | Deve R$ %.2f | Saldo: R$ %.2f%n",
                saldo.getUsuario().getNome(),
                saldo.getTotalPago(),
                saldo.getTotalDevido(),
                saldo.getSaldoFinal());
        }
        
        System.out.println("\n=== ACERTOS ===");
        for (Saldo saldo : saldos) {
            if (saldo.getSaldoFinal().compareTo(BigDecimal.ZERO) < 0) {
                System.out.printf("%s deve R$ %.2f%n", 
                    saldo.getUsuario().getNome(), 
                    saldo.getSaldoFinal().abs());
            } else if (saldo.getSaldoFinal().compareTo(BigDecimal.ZERO) > 0) {
                System.out.printf("%s tem a receber R$ %.2f%n", 
                    saldo.getUsuario().getNome(), 
                    saldo.getSaldoFinal());
            }
        }
    }
}