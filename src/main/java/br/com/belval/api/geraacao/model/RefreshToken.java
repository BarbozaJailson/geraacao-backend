package br.com.belval.api.geraacao.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime expiryDate;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Getters e setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getToken() {return token;}
    public void setToken(String token) {this.token = token;}

    public LocalDateTime getExpiryDate() {return expiryDate;}
    public void setExpiryDate(LocalDateTime expiryDate) {this.expiryDate = expiryDate;}

    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}
}

