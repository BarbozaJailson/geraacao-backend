package br.com.belval.api.geraacao.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class RequisicaoUpdateDTO {

    @PositiveOrZero(message = "A quantidade deve ser zero ou maior")
    private Integer quantidade;
    private String status;
    @Column(name = "ativo", nullable = false, length = 1)
    private Boolean ativo;
    @NotNull(message = "O ID do item é obrigatório")
    private Integer itemId;
    @NotNull(message = "O ID da instituição é obrigatório")
    private Integer instituicaoId;

    // Getters e Setters
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean isAtivo() {return ativo;}
    public void setAtivo(Boolean ativo) {this.ativo = ativo;}

    public Integer getItemId() {
        return itemId;
    }
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getInstituicaoId() {
        return instituicaoId;
    }
    public void setInstituicaoId(Integer instituicaoId) {
        this.instituicaoId = instituicaoId;
    }
}

