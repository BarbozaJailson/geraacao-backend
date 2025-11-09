package br.com.belval.api.geraacao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Campanha")
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(name = "descricao", length = 255, nullable = false)
    private String descricao;
    @NotNull(message = "A data do evento é obrigatória")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_evento", nullable = false)
    private LocalDate dataEvento;
    @NotBlank
    @Column(name = "tipo_logradouro", length = 15, nullable = false)
    private String tipoLogradouro;
    @NotBlank
    @Column(name = "logradouro", length = 100, nullable = false)
    private String logradouro;
    @NotBlank
    @Column(name = "numero", length = 8, nullable = false)
    private String numero;
    @NotBlank
    @Pattern(regexp = "\\d{8}")
    @Column(name = "cep", length = 8, nullable = false)
    @Size(min = 8, max = 8)
    private String cep;
    @NotBlank
    @Column(name = "bairro", length = 30, nullable = false)
    private String bairro;
    @NotBlank
    @Column(name = "cidade", length = 30, nullable = false)
    private String cidade;
    @NotBlank
    @Column(name = "uf", length = 2, nullable = false)
    @Size(min = 2, max = 2)
    private String uf;
    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;
    @Column(name = "ativo", nullable = false, length = 1)
    private Boolean ativo;
    //Relacionamento com 'Item'
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_item", nullable = false)
    @NotNull(message = "O Item é obrigatório!")
    private Item item;
    //Relacionamento com 'Instituição'
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_instituicao")
    @NotNull(message = "Instituição é obrigatoria")
    private Instituicao instituicao;
    // Relacionamento com 'MovimentacaoEstoque'
    @OneToMany(mappedBy = "campanha", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<MovimentacaoEstoque> movimentacaoEstoque;

    public Campanha() {}

    public Campanha(Integer id, String descricao, LocalDate dataEvento, String tipoLogradouro, String logradouro, String numero, String cep, String bairro, String cidade, String uf, Integer quantidade, LocalDate dataCadastro, Item item, Instituicao instituicao, List<MovimentacaoEstoque> movimentacaoEstoque, Boolean ativo) {
        this.id = id;
        this.descricao = descricao;
        this.dataEvento = dataEvento;
        this.tipoLogradouro = tipoLogradouro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.quantidade = quantidade;
        this.dataCadastro = dataCadastro;
        this.item = item;
        this.instituicao = instituicao;
        this.movimentacaoEstoque = movimentacaoEstoque;
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
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
    public LocalDate getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    public Boolean isAtivo() {return ativo;}
    public void setAtivo(Boolean ativo) {this.ativo = ativo;}
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public Instituicao getInstituicao() {
        return instituicao;
    }
    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
    public List<MovimentacaoEstoque> getMovimentacaoEstoque() {
        return movimentacaoEstoque;
    }
    public void setMovimentacaoEstoque(List<MovimentacaoEstoque> movimentacaoEstoque) {
        this.movimentacaoEstoque = movimentacaoEstoque;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campanha campanha = (Campanha) o;
        return Objects.equals(id, campanha.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
