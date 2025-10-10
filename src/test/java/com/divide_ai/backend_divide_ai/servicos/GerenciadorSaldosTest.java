package com.divide_ai.backend_divide_ai.servicos;

import com.divide_ai.backend_divide_ai.entidades.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

public class GerenciadorSaldosTest {

    private GerenciadorSaldos gerenciador;
    private Usuario joao;
    private Usuario maria;
    private Grupo grupo;

    @BeforeEach
    public void setUp() {
        gerenciador = new GerenciadorSaldos();
        joao = new Usuario(1L, "João", "joao@email.com", "11999999999");
        maria = new Usuario(2L, "Maria", "maria@email.com", "11888888888");
        
        grupo = new Grupo(1L, "Teste", "Grupo de teste");
        grupo.getMembros().add(joao);
        grupo.getMembros().add(maria);
    }

    @Test
    public void testCalcularSaldosComDespesas() {
        // João paga R$ 120, Maria paga R$ 80
        // Ambos participam de todas as despesas
        Despesa despesa1 = new Despesa(1L, "Jantar", new BigDecimal("120.00"), joao, grupo);
        despesa1.getParticipantes().add(joao);
        despesa1.getParticipantes().add(maria);
        
        Despesa despesa2 = new Despesa(2L, "Combustível", new BigDecimal("80.00"), maria, grupo);
        despesa2.getParticipantes().add(joao);
        despesa2.getParticipantes().add(maria);
        
        grupo.getDespesas().add(despesa1);
        grupo.getDespesas().add(despesa2);

        List<Saldo> saldos = gerenciador.calcularSaldos(grupo);

        // Verificar se há 2 saldos (um para cada usuário)
        assertEquals(2, saldos.size());
        
        // Encontrar saldo do João
        Saldo saldoJoao = saldos.stream()
            .filter(s -> s.getUsuario().equals(joao))
            .findFirst()
            .orElse(null);
            
        assertNotNull(saldoJoao);
        assertEquals(new BigDecimal("120.00"), saldoJoao.getTotalPago());
        assertEquals(new BigDecimal("100.00"), saldoJoao.getTotalDevido()); // (120+80)/2
    }

    @Test
    public void testCalcularSaldosGrupoVazio() {
        // Grupo sem despesas
        List<Saldo> saldos = gerenciador.calcularSaldos(grupo);

        // Deve retornar saldos zerados para cada membro
        assertEquals(2, saldos.size());
        
        for (Saldo saldo : saldos) {
            assertEquals(BigDecimal.ZERO, saldo.getTotalPago());
            assertEquals(BigDecimal.ZERO, saldo.getTotalDevido());
        }
    }

    @Test
    public void testCalcularSaldoFinal() {
        Saldo saldo = new Saldo(1L, joao, grupo);
        saldo.setTotalPago(new BigDecimal("150.00"));
        saldo.setTotalDevido(new BigDecimal("100.00"));

        saldo.calcularSaldo();

        // Saldo final deve ser 150 - 100 = 50
        assertEquals(new BigDecimal("50.00"), saldo.getSaldoFinal());
    }
}