package com.divide_ai.backend_divide_ai.servicos;


import com.divide_ai.backend_divide_ai.entidades.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class CalculadoraDivisao {
    
    public Map<Usuario, BigDecimal> calcularDivisao(Despesa despesa) {
        switch (despesa.getTipoDivisao()) {
            case IGUAL:
                return calcularDivisaoIgual(despesa);
            case PERSONALIZADA:
                return calcularDivisaoPersonalizada(despesa);
            case PORCENTAGEM:
                return calcularDivisaoPorcentagem(despesa);
            default:
                return calcularDivisaoIgual(despesa);
        }
    }
    
    private Map<Usuario, BigDecimal> calcularDivisaoIgual(Despesa despesa) {
        Map<Usuario, BigDecimal> divisao = new HashMap<>();
        int numParticipantes = despesa.getParticipantes().size();
        
        if (numParticipantes == 0) return divisao;
        
        BigDecimal valorPorPessoa = despesa.getValor()
            .divide(BigDecimal.valueOf(numParticipantes), 2, RoundingMode.HALF_UP);
        
        for (Usuario participante : despesa.getParticipantes()) {
            divisao.put(participante, valorPorPessoa);
        }
        
        return divisao;
    }
    
    private Map<Usuario, BigDecimal> calcularDivisaoPersonalizada(Despesa despesa) {
        Map<Usuario, BigDecimal> divisao = new HashMap<>();
        // Exemplo: João paga 70%, Maria paga 30%
        BigDecimal valorJoao = despesa.getValor().multiply(new BigDecimal("0.70"));
        BigDecimal valorMaria = despesa.getValor().multiply(new BigDecimal("0.30"));
        
        for (Usuario participante : despesa.getParticipantes()) {
            if (participante.getNome().equals("João")) {
                divisao.put(participante, valorJoao);
            } else {
                divisao.put(participante, valorMaria);
            }
        }
        return divisao;
    }
    
    private Map<Usuario, BigDecimal> calcularDivisaoPorcentagem(Despesa despesa) {
        return calcularDivisaoPersonalizada(despesa);
    }
}