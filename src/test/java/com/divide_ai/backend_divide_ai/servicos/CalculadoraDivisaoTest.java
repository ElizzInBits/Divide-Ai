package com.divide_ai.backend_divide_ai.servicos;

import com.divide_ai.backend_divide_ai.entidades.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Map;

public class CalculadoraDivisaoTest {

    private CalculadoraDivisao calculadora;
    private Usuario joao;
    private Usuario maria;
    private Grupo grupo;

    @BeforeEach
    public void setUp() {
        calculadora = new CalculadoraDivisao();
        joao = new Usuario(1L, "João", "joao@email.com", "11999999999");
        maria = new Usuario(2L, "Maria", "maria@email.com", "11888888888");
        
        grupo = new Grupo(1L, "Teste", "Grupo de teste");
    }

    @Test
    public void testDivisaoIgual() {
        // Criar despesa de R$ 100 para dividir igualmente
        Despesa despesa = new Despesa(1L, "Jantar", new BigDecimal("100.00"), joao, grupo);
        despesa.getParticipantes().add(joao);
        despesa.getParticipantes().add(maria);
        despesa.setTipoDivisao(TipoDivisao.IGUAL);

        // Executar cálculo
        Map<Usuario, BigDecimal> resultado = calculadora.calcularDivisao(despesa);

        // Verificar se cada um deve pagar R$ 50
        assertEquals(new BigDecimal("50.00"), resultado.get(joao));
        assertEquals(new BigDecimal("50.00"), resultado.get(maria));
    }

    @Test
    public void testDivisaoPersonalizada() {
        Despesa despesa = new Despesa(2L, "Hotel", new BigDecimal("200.00"), joao, grupo);
        despesa.getParticipantes().add(joao);
        despesa.getParticipantes().add(maria);
        despesa.setTipoDivisao(TipoDivisao.PERSONALIZADA);

        Map<Usuario, BigDecimal> resultado = calculadora.calcularDivisao(despesa);

        // Verificar divisão personalizada (70% João, 30% Maria)
        assertEquals(0, new BigDecimal("140.00").compareTo(resultado.get(joao)));
        assertEquals(0, new BigDecimal("60.00").compareTo(resultado.get(maria)));
    }

    @Test
    public void testDespesaComUmParticipante() {
        // Despesa só do João
        Despesa despesa = new Despesa(3L, "Lanche", new BigDecimal("20.00"), joao, grupo);
        despesa.getParticipantes().add(joao);

        Map<Usuario, BigDecimal> resultado = calculadora.calcularDivisao(despesa);

        // Verificar se só João paga
        assertEquals(new BigDecimal("20.00"), resultado.get(joao));
        assertNull(resultado.get(maria));
    }

    @Test
    public void testDespesaSemParticipantes() {
        Despesa despesa = new Despesa(4L, "Vazia", new BigDecimal("50.00"), joao, grupo);
        // Não adicionar participantes

        Map<Usuario, BigDecimal> resultado = calculadora.calcularDivisao(despesa);

        // Resultado deve estar vazio
        assertTrue(resultado.isEmpty());
    }
}