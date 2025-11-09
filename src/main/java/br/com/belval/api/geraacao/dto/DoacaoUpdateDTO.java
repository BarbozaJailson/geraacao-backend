package br.com.belval.api.geraacao.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class DoacaoUpdateDTO {

    @Size(max = 15, message = "O Status deve ter no máximo 15 caracteres")
    private String status;
    @PositiveOrZero(message = "A quantidade deve ser zero ou maior")
    private Integer quantidade;
    private Integer usuarioId;
    private Integer requisicaoId;
    private Boolean ativo;
    // Getters e Setters

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public Integer getQuantidade() {return quantidade;}
    public void setQuantidade(Integer quantidade) {this.quantidade = quantidade;}

    public Integer getUsuarioId() {return usuarioId;}
    public void setUsuarioId(Integer usuarioId) {this.usuarioId = usuarioId;}

    public Integer getRequisicaoId() {return requisicaoId;}
    public void setRequisicaoId(Integer requisicaoId) {this.requisicaoId = requisicaoId;}

    public Boolean isAtivo() {return ativo;}
    public void setAtivo(Boolean ativo) {this.ativo = ativo;}
}

