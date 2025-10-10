package com.divide_ai.backend_divide_ai.entidades;

import java.math.BigDecimal;

public class Saldo {
    private Usuario usuario;
    private BigDecimal totalPago;
    private BigDecimal totalDevido;
    private BigDecimal saldoFinal;
    
    public Saldo() {
        this.totalPago = BigDecimal.ZERO;
        this.totalDevido = BigDecimal.ZERO;
        this.saldoFinal = BigDecimal.ZERO;
    }
    
    public Saldo(Long id, Usuario usuario, Grupo grupo) {
        this();
        this.usuario = usuario;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public BigDecimal getTotalPago() {
        return totalPago;
    }
    
    public void setTotalPago(BigDecimal totalPago) {
        this.totalPago = totalPago;
    }
    
    public BigDecimal getTotalDevido() {
        return totalDevido;
    }
    
    public void setTotalDevido(BigDecimal totalDevido) {
        this.totalDevido = totalDevido;
    }
    
    public BigDecimal getSaldoFinal() {
        return saldoFinal;
    }
    
    public void calcularSaldo() {
        this.saldoFinal = this.totalPago.subtract(this.totalDevido);
    }
}