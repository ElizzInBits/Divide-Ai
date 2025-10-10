package com.divide_ai.backend_divide_ai.entidades;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void testCriarUsuario() {
        // ARRANGE - Preparar dados
        Long id = 1L;
        String nome = "João";
        String email = "joao@email.com";
        String telefone = "11999999999";

        // ACT - Executar ação
        Usuario usuario = new Usuario(id, nome, email, telefone);

        // ASSERT - Verificar resultado
        assertEquals(id, usuario.getId());
        assertEquals(nome, usuario.getNome());
    }

    @Test
    public void testEqualsUsuarios() {
        Usuario usuario1 = new Usuario(1L, "João", "joao@email.com", "11999999999");
        Usuario usuario2 = new Usuario(1L, "Maria", "maria@email.com", "11888888888");

        // Usuários com mesmo ID devem ser iguais
        assertEquals(usuario1, usuario2);
    }

    @Test
    public void testHashCodeUsuarios() {
        Usuario usuario1 = new Usuario(1L, "João", "joao@email.com", "11999999999");
        Usuario usuario2 = new Usuario(1L, "João", "joao@email.com", "11999999999");

        // Usuários iguais devem ter mesmo hashCode
        assertEquals(usuario1.hashCode(), usuario2.hashCode());
    }

    @Test
    public void testUsuariosDiferentes() {
        Usuario usuario1 = new Usuario(1L, "João", "joao@email.com", "11999999999");
        Usuario usuario2 = new Usuario(2L, "Maria", "maria@email.com", "11888888888");

        // Usuários com IDs diferentes devem ser diferentes
        assertNotEquals(usuario1, usuario2);
    }
}