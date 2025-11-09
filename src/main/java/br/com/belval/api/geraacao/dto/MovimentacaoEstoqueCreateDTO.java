package br.com.belval.api.geraacao.dto;

import java.time.LocalDateTime;

import br.com.belval.api.geraacao.model.TipoMovimentacao;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MovimentacaoEstoqueCreateDTO {

    @NotNull(message = "Id do item é obrigatório")
    private Integer itemId;
    @Min(value = 1, message = "Quantidade deve ser maior que zero")
    private int quantidade;
    @NotNull(message = "Tipo de movimentação é obrigatório")
    private TipoMovimentacao tipoMovimentacao;
    private LocalDateTime dataMovimentacao; // opcional
    @Size(max = 255)
    private String observacao;
    private Integer requisicaoId;
    private Integer doacaoId;
    private Integer instituicaoId;

    public MovimentacaoEstoqueCreateDTO() {}

    // getters e setters
    public Integer getItemId() {
        return itemId;
    }
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }
    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }
    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getRequisicaoId() {
        return requisicaoId;
    }
    public void setRequisicaoId(Integer requisicaoId) {
        this.requisicaoId = requisicaoId;
    }

    public Integer getDoacaoId() {
        return doacaoId;
    }
    public void setDoacaoId(Integer doacaoId) {
        this.doacaoId = doacaoId;
    }

    public Integer getInstituicaoId() {
        return instituicaoId;
    }
    public void setInstituicaoId(Integer instituicaoId) {this.instituicaoId = instituicaoId;}


}






