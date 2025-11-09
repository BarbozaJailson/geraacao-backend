package br.com.belval.api.geraacao.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class RequisicaoCreateDTO {

    @PositiveOrZero(message = "A quantidade deve ser zero ou maior")
    private int quantidade;
    private String status;
    @PositiveOrZero(message = "A quantidade deve ser zero ou maior")
    private int quantidadeAtendida;
    @Column(name = "ativo", nullable = false, length = 1)
    private Boolean ativo;
    @NotNull(message = "O ID do item é obrigatório")
    private Integer itemId;
    @NotNull(message = "O ID da instituição é obrigatório")
    private Integer instituicaoId;

    // Getters e Setters
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidadeAtendida() {
        return quantidadeAtendida;
    }
    public void setQuantidadeAtendida(int quantidadeAtendida) {
        this.quantidadeAtendida = quantidadeAtendida;
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

