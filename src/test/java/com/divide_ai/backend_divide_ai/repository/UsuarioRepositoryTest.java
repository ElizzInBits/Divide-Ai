package com.divide_ai.backend_divide_ai.repository;

import com.divide_ai.backend_divide_ai.entidades.Usuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioRepositoryTest {

    @Test
    public void testCriarUsuarioParaRepository() {
        // ARRANGE - Preparar dados
        Long id = 1L;
        String nome = "João";
        String email = "joao@email.com";
        String telefone = "11999999999";

        // ACT - Criar usuário
        Usuario usuario = new Usuario(id, nome, email, telefone);

        // ASSERT - Verificar se usuário foi criado corretamente
        assertNotNull(usuario);
        assertEquals(id, usuario.getId());
        assertEquals(nome, usuario.getNome());
    }

    @Test
    public void testUsuarioValidoParaSalvar() {
        // ARRANGE - Criar usuário válido
        Usuario usuario = new Usuario(1L, "Maria", "maria@email.com", "11888888888");

        // ASSERT - Verificar se todos os campos estão preenchidos
        assertNotNull(usuario.getId());
        assertNotNull(usuario.getNome());
        assertFalse(usuario.getNome().isEmpty());
    }

    @Test
    public void testUsuariosComMesmoId() {
        // ARRANGE - Criar dois usuários com mesmo ID
        Usuario usuario1 = new Usuario(1L, "João", "joao@email.com", "11999999999");
        Usuario usuario2 = new Usuario(1L, "Pedro", "pedro@email.com", "11777777777");

        // ASSERT - Devem ser considerados iguais (mesmo ID)
        assertEquals(usuario1, usuario2);
        assertEquals(usuario1.hashCode(), usuario2.hashCode());
    }

    @Test
    public void testUsuariosComIdsDiferentes() {
        // ARRANGE - Criar usuários com IDs diferentes
        Usuario usuario1 = new Usuario(1L, "João", "joao@email.com", "11999999999");
        Usuario usuario2 = new Usuario(2L, "Maria", "maria@email.com", "11888888888");

        // ASSERT - Devem ser diferentes
        assertNotEquals(usuario1, usuario2);
    }
}