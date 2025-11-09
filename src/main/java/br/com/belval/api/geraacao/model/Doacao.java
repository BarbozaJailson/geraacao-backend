package br.com.belval.api.geraacao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Doacao")
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "status", nullable = false, length = 15)
    @NotBlank(message = "O Status é obrigatório")
    @Size(max = 15, message = "O Status deve ter no máximo 15 caracteres")
    private String status;
    @PositiveOrZero(message = "A quantidade deve ser zero ou maior")
    @Column(name = "quantidade", nullable = false)
    private int quantidade;
    @Column(name = "data")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    @Column(name = "ativo", nullable = false, length = 1)
    private Boolean ativo;
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @NotNull(message = "O Usuario é obrigatório")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_requisicao", nullable = false)
    @NotNull(message = "A Requisicao é obrigatória")
    private Requisicao requisicao;

    //	Construtor Vazio
    public Doacao() {

    }

    //	Construtor
    public Doacao(Integer id, String status, int quantidade, LocalDate data, Usuario usuario, Requisicao requisicao, Boolean ativo) {
        super();
        this.id = id;
        this.status = status;
        this.quantidade = quantidade;
        this.data = data;
        this.usuario = usuario;
        this.requisicao = requisicao;
        this.ativo = ativo;
    }

    @PrePersist
    public void prePersist() {
        if (this.data == null) {
            this.data = LocalDate.now();
        }
        if (this.status == null || this.status.isBlank()) {
            this.status = "Pendente";
        }
    }

    //	Getters and Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Requisicao getRequisicao() {
        return requisicao;
    }
    public void setRequisicao(Requisicao requisicao) {
        this.requisicao = requisicao;
    }
    public Boolean isAtivo() {return ativo;}
    public void setAtivo(Boolean ativo) {this.ativo = ativo;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doacao)) return false;
        Doacao doacao = (Doacao) o;
        return id != null && id.equals(doacao.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}

