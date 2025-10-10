package com.divide_ai.backend_divide_ai;

import com.divide_ai.backend_divide_ai.entidades.*;
import com.divide_ai.backend_divide_ai.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class DataViewer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private GrupoRepository grupoRepository;
    
    @Autowired
    private DespesaRepository despesaRepository;

    @Override
    public void run(String... args) {
        System.out.println("\n📊 ===== VISUALIZANDO DADOS DO DYNAMODB =====");
        
        // Buscar e exibir usuários
        System.out.println("\n👥 USUÁRIOS CADASTRADOS:");
        System.out.println("┌─────┬─────────────────┬─────────────────────┬─────────────────┐");
        System.out.println("│ ID  │ Nome            │ Email               │ Telefone        │");
        System.out.println("├─────┼─────────────────┼─────────────────────┼─────────────────┤");
        
        for (long i = 1; i <= 4; i++) {
            try {
                Usuario usuario = usuarioRepository.buscarPorId(i);
                if (usuario != null) {
                    System.out.printf("│ %-3d │ %-15s │ %-19s │ %-15s │%n", 
                        i, usuario.getNome(), "***@email.com", "11*********");
                }
            } catch (Exception e) {
                // Usuário não encontrado
            }
        }
        System.out.println("└─────┴─────────────────┴─────────────────────┴─────────────────┘");
        
        // Buscar e exibir grupos
        System.out.println("\n🏖️ GRUPOS CADASTRADOS:");
        System.out.println("┌─────┬─────────────────────┬─────────────────────────────────────┐");
        System.out.println("│ ID  │ Nome                │ Descrição                           │");
        System.out.println("├─────┼─────────────────────┼─────────────────────────────────────┤");
        
        for (long i = 1; i <= 2; i++) {
            try {
                Grupo grupo = grupoRepository.buscarPorId(i);
                if (grupo != null) {
                    System.out.printf("│ %-3d │ %-19s │ %-35s │%n", 
                        i, grupo.getNome(), "Grupo de despesas");
                }
            } catch (Exception e) {
                // Grupo não encontrado
            }
        }
        System.out.println("└─────┴─────────────────────┴─────────────────────────────────────┘");
        
        // Buscar e exibir despesas
        System.out.println("\n💰 DESPESAS CADASTRADAS:");
        System.out.println("┌─────┬─────────────────────┬─────────────┬─────────────────┐");
        System.out.println("│ ID  │ Descrição           │ Valor       │ Tipo Divisão    │");
        System.out.println("├─────┼─────────────────────┼─────────────┼─────────────────┤");
        
        for (long i = 1; i <= 6; i++) {
            try {
                Despesa despesa = despesaRepository.buscarPorId(i);
                if (despesa != null) {
                    System.out.printf("│ %-3d │ %-19s │ R$ %-8s │ %-15s │%n", 
                        i, despesa.getDescricao(), despesa.getValor(), despesa.getTipoDivisao());
                }
            } catch (Exception e) {
                // Despesa não encontrada
            }
        }
        System.out.println("└─────┴─────────────────────┴─────────────┴─────────────────┘");
        
        System.out.println("\n🎯 DADOS CARREGADOS DO AWS DYNAMODB!");
        System.out.println("🌐 Para ver mais detalhes, acesse: https://console.aws.amazon.com/dynamodb");
    }
}