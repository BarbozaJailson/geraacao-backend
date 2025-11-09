package br.com.belval.api.geraacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class CampanhaCreateDTO {

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 255)
    private String descricao;
    @NotNull(message = "A data do evento é obrigatória")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataEvento;
    @NotBlank(message = "O tipo de logradouro é obrigatório")
    @Size(max = 15)
    private String tipoLogradouro;
    @NotBlank(message = "O logradouro é obrigatório")
    @Size(max = 100)
    private String logradouro;
    @NotBlank(message = "O número é obrigatório")
    @Size(max = 8)
    private String numero;
    @NotBlank(message = "O CEP é obrigatório")
    @Pattern(regexp = "\\d{8}", message = "O CEP deve conter 8 dígitos numéricos")
    private String cep;
    @NotBlank(message = "O bairro é obrigatório")
    @Size(max = 30)
    private String bairro;
    @NotBlank(message = "A cidade é obrigatória")
    @Size(max = 30)
    private String cidade;
    @NotBlank(message = "A UF é obrigatória")
    @Size(min = 2, max = 2, message = "A UF deve ter 2 caracteres")
    private String uf;
    @NotNull(message = "A quantidade é obrigatória")
    @PositiveOrZero(message = "A quantidade não pode ser negativa")
    private Integer quantidade;
    @Column(name = "ativo", nullable = false, length = 1)
    private Boolean ativo;
    @NotNull(message = "O item é obrigatório")
    private Integer itemId;
    @NotNull(message = "A instituição é obrigatória")
    private Integer instituicaoId;

    // Getters e Setters
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }
    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }
    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
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
