package br.com.belval.api.geraacao.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class DoacaoCreateDTO {

    @NotBlank(message = "O Status é obrigatório")
    @Size(max = 15, message = "O Status deve ter no máximo 15 caracteres")
    private String status;
    @PositiveOrZero(message = "A quantidade deve ser zero ou maior")
    @NotNull(message = "A quantidade é obrigatória")
    private Integer quantidade;
    @Column(name = "ativo", nullable = false, length = 1)
    private Boolean ativo;
    @NotNull(message = "O id do usuário é obrigatório")
    private Integer usuarioId;
    @NotNull(message = "O id da requisição é obrigatório")
    private Integer requisicaoId;

    // Getters e Setters

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public Integer getQuantidade() {return quantidade;}
    public void setQuantidade(Integer quantidade) {this.quantidade = quantidade;}

    public Boolean isAtivo() {return ativo;}
    public void setAtivo(Boolean ativo) {this.ativo = ativo;}

    public Integer getUsuarioId() {return usuarioId;}
    public void setUsuarioId(Integer usuarioId) {this.usuarioId = usuarioId;}

    public Integer getRequisicaoId() {return requisicaoId;}
    public void setRequisicaoId(Integer requisicaoId) {this.requisicaoId = requisicaoId;}
}


