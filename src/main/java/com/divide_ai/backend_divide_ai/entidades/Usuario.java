package com.divide_ai.backend_divide_ai.entidades;

import java.util.Objects;

public class Usuario {
    private Long id;
    private String nome;
    
    public Usuario() {}
    
    public Usuario(Long id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}