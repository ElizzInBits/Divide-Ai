import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.divide_ai.backend_divide_ai.entidades.*;
import com.divide_ai.backend_divide_ai.servicos.*;
import com.divide_ai.backend_divide_ai.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// @Component // Desabilitado - usando H2
@Order(1)
public class Main implements CommandLineRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private GrupoRepository grupoRepository;
    
    @Autowired
    private DespesaRepository despesaRepository;

    @Override
    public void run(String... args) {
        System.out.println("=== DIVIDE AI - SISTEMA DE DIVISAO DE CONTAS ===");
        
        // Criar e salvar usuários no DynamoDB
        Usuario joao = new Usuario(1L, "João", "joao@email.com", "11999999999");
        Usuario maria = new Usuario(2L, "Maria", "maria@email.com", "11888888888");
        
        System.out.println("\n💾 Salvando usuários no DynamoDB...");
        usuarioRepository.salvar(joao);
        usuarioRepository.salvar(maria);
        System.out.println("✅ Usuários salvos: João e Maria");
        
        // Criar e salvar grupo no DynamoDB
        Grupo grupo = new Grupo(1L, "Viagem Praia", "Gastos da viagem para a praia");
        grupo.getMembros().add(joao);
        grupo.getMembros().add(maria);
        
        System.out.println("\n💾 Salvando grupo no DynamoDB...");
        grupoRepository.salvar(grupo);
        System.out.println("✅ Grupo salvo: " + grupo.getNome());
        
        // Criar e salvar despesas no DynamoDB
        Despesa despesa1 = new Despesa(1L, "Jantar no restaurante", new BigDecimal("120.00"), joao, grupo);
        despesa1.getParticipantes().add(joao);
        despesa1.getParticipantes().add(maria);
        despesaRepository.salvar(despesa1);
        
        Despesa despesa2 = new Despesa(2L, "Combustivel", new BigDecimal("80.00"), maria, grupo);
        despesa2.getParticipantes().add(joao);
        despesa2.getParticipantes().add(maria);
        despesaRepository.salvar(despesa2);
        
        // Testar divisão personalizada
        Despesa despesa3 = new Despesa(3L, "Hotel", new BigDecimal("200.00"), joao, grupo);
        despesa3.setTipoDivisao(TipoDivisao.PERSONALIZADA);
        despesa3.getParticipantes().add(joao);
        despesa3.getParticipantes().add(maria);
        System.out.println("\n💾 Salvando despesas no DynamoDB...");
        despesaRepository.salvar(despesa3);
        System.out.println("✅ Despesas salvas: Jantar, Combustível, Hotel");
        
        // Adicionar despesas ao grupo
        grupo.getDespesas().add(despesa1);
        grupo.getDespesas().add(despesa2);
        grupo.getDespesas().add(despesa3);
        System.out.println("\n💾 Atualizando grupo com despesas...");
        grupoRepository.salvar(grupo);
        System.out.println("✅ Grupo atualizado no DynamoDB");
        
        // Inicializar serviços
        CalculadoraDivisao calculadora = new CalculadoraDivisao();
        GerenciadorSaldos gerenciadorSaldos = new GerenciadorSaldos();
        GerenciadorNotificacoes gerenciadorNotificacoes = new GerenciadorNotificacoes();
        
        // Exibir informações básicas
        System.out.println("\nGrupo: " + grupo.getNome());
        System.out.println("Membros: " + grupo.getMembros().size());
        System.out.println("Total de despesas: " + grupo.getDespesas().size());
        
        // Calcular e exibir divisões
        System.out.println("\n=== DIVISAO DAS DESPESAS ===");
        for (Despesa despesa : grupo.getDespesas()) {
            System.out.println("\n" + despesa.getDescricao() + " - R$ " + despesa.getValor() + " (" + despesa.getTipoDivisao() + ")");
            System.out.println("Pagador: " + despesa.getPagador().getNome());
            
            Map<Usuario, BigDecimal> divisao = calculadora.calcularDivisao(despesa);
            for (Map.Entry<Usuario, BigDecimal> entry : divisao.entrySet()) {
                System.out.printf("  %s deve pagar: R$ %.2f%n", entry.getKey().getNome(), entry.getValue());
            }
            
            // Gerar notificações
            gerenciadorNotificacoes.notificarNovaDespesa(despesa);
        }
        
        // Calcular e exibir saldos
        List<Saldo> saldos = gerenciadorSaldos.calcularSaldos(grupo);
        gerenciadorSaldos.exibirSaldos(saldos);
        
        // Gerar notificações de saldo
        gerenciadorNotificacoes.notificarSaldoAtualizado(saldos);
        
        // Exibir notificações
        gerenciadorNotificacoes.exibirNotificacoes();
        
        System.out.println("\n🎉 Sistema completo funcionando com DynamoDB!");
        System.out.println("🚀 Todos os dados foram salvos no AWS DynamoDB!");
        System.out.println("📊 Verifique suas tabelas no console AWS para ver os dados!");
    }
}