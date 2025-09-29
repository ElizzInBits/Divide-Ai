package com.divide_ai.backend_divide_ai.entidades;

public class Notificacao {
    private String titulo;
    private String mensagem;
    private Usuario destinatario;
    
    public Notificacao() {}
    
    public Notificacao(Long id, String titulo, String mensagem, Usuario destinatario, TipoNotificacao tipo) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.destinatario = destinatario;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public String getMensagem() {
        return mensagem;
    }
    
    public Usuario getDestinatario() {
        return destinatario;
    }
}