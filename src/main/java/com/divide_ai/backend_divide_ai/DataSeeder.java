package com.divide_ai.backend_divide_ai;

import com.divide_ai.backend_divide_ai.entidades.*;
import com.divide_ai.backend_divide_ai.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

// @Component // Desabilitado temporariamente
@Order(2)
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private GrupoRepository grupoRepository;
    
    @Autowired
    private DespesaRepository despesaRepository;

    @Override
    public void run(String... args) {
        System.out.println("\n🌱 INSERINDO DADOS DE EXEMPLO...");
        
        // Criar usuários
        Usuario joao = new Usuario(1L, "João Silva", "joao@email.com", "11999999999");
        Usuario maria = new Usuario(2L, "Maria Santos", "maria@email.com", "11888888888");
        Usuario pedro = new Usuario(3L, "Pedro Costa", "pedro@email.com", "11777777777");
        Usuario ana = new Usuario(4L, "Ana Oliveira", "ana@email.com", "11666666666");
        
        usuarioRepository.salvar(joao);
        usuarioRepository.salvar(maria);
        usuarioRepository.salvar(pedro);
        usuarioRepository.salvar(ana);
        System.out.println("✅ 4 usuários inseridos");
        
        // Criar grupos
        Grupo viagemPraia = new Grupo(1L, "Viagem Praia", "Gastos da viagem para a praia");
        viagemPraia.getMembros().add(joao);
        viagemPraia.getMembros().add(maria);
        
        Grupo churrascoAmigos = new Grupo(2L, "Churrasco Amigos", "Churrasco de fim de semana");
        churrascoAmigos.getMembros().add(pedro);
        churrascoAmigos.getMembros().add(ana);
        churrascoAmigos.getMembros().add(joao);
        
        grupoRepository.salvar(viagemPraia);
        grupoRepository.salvar(churrascoAmigos);
        System.out.println("✅ 2 grupos inseridos");
        
        // Criar despesas - Viagem Praia
        Despesa jantar = new Despesa(1L, "Jantar no restaurante", new BigDecimal("120.00"), joao, viagemPraia);
        jantar.getParticipantes().add(joao);
        jantar.getParticipantes().add(maria);
        
        Despesa combustivel = new Despesa(2L, "Combustível", new BigDecimal("80.00"), maria, viagemPraia);
        combustivel.getParticipantes().add(joao);
        combustivel.getParticipantes().add(maria);
        
        Despesa hotel = new Despesa(3L, "Hotel", new BigDecimal("200.00"), joao, viagemPraia);
        hotel.setTipoDivisao(TipoDivisao.PERSONALIZADA);
        hotel.getParticipantes().add(joao);
        hotel.getParticipantes().add(maria);
        
        // Criar despesas - Churrasco
        Despesa carne = new Despesa(4L, "Carne", new BigDecimal("150.00"), pedro, churrascoAmigos);
        carne.getParticipantes().add(pedro);
        carne.getParticipantes().add(ana);
        carne.getParticipantes().add(joao);
        
        Despesa bebidas = new Despesa(5L, "Bebidas", new BigDecimal("90.00"), ana, churrascoAmigos);
        bebidas.getParticipantes().add(pedro);
        bebidas.getParticipantes().add(ana);
        bebidas.getParticipantes().add(joao);
        
        Despesa carvao = new Despesa(6L, "Carvão", new BigDecimal("30.00"), joao, churrascoAmigos);
        carvao.getParticipantes().add(pedro);
        carvao.getParticipantes().add(ana);
        carvao.getParticipantes().add(joao);
        
        // Salvar despesas
        despesaRepository.salvar(jantar);
        despesaRepository.salvar(combustivel);
        despesaRepository.salvar(hotel);
        despesaRepository.salvar(carne);
        despesaRepository.salvar(bebidas);
        despesaRepository.salvar(carvao);
        System.out.println("✅ 6 despesas inseridas");
        
        // Atualizar grupos com despesas
        viagemPraia.getDespesas().add(jantar);
        viagemPraia.getDespesas().add(combustivel);
        viagemPraia.getDespesas().add(hotel);
        
        churrascoAmigos.getDespesas().add(carne);
        churrascoAmigos.getDespesas().add(bebidas);
        churrascoAmigos.getDespesas().add(carvao);
        
        grupoRepository.salvar(viagemPraia);
        grupoRepository.salvar(churrascoAmigos);
        
        System.out.println("🎉 DADOS DE EXEMPLO INSERIDOS COM SUCESSO!");
        System.out.println("📊 Verifique o console AWS DynamoDB para ver os dados!");
        System.out.println("👥 4 usuários | 🏖️ 2 grupos | 💰 6 despesas");
    }
}