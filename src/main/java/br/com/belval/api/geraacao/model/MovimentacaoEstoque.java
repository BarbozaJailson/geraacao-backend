package br.com.belval.api.geraacao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Movimentacao_Estoque")
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Quantidade não pode estar vazia")
    @Column(name = "quantidade")
    private int quantidade;
    @NotNull(message = "Tipo movimentacao é obrigatoria")
    @Column(name = "tipo_movimentacao")
    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipoMovimentacao;
    @NotNull(message = "Data momvimentacao não pode estar vazia")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "data_movimentacao")
    private LocalDateTime dataMovimentacao;
    @Size(max = 255, message = "O campo observação deve ter no maximo 255 caracteres")
    @Column(name = "observacao")
    private String observacao;
    @ManyToOne
    @JoinColumn(name = "id_item", nullable = false)
    private Item item;
    @ManyToOne
    @JoinColumn(name = "id_requisicao", nullable = true)
    private Requisicao requisicao;
    @ManyToOne
    @JoinColumn(name = "id_doacao", nullable = true)
    private Doacao doacao;
    @ManyToOne
    @JoinColumn(name = "id_instituicao", nullable = false)
    private Instituicao instituicao;
    @Column(name = "ativo", nullable = false, length = 1)
    private Boolean ativo;
    @ManyToOne
    @JoinColumn(name = "id_campanha")
    private Campanha campanha;

    public MovimentacaoEstoque() {}

    public MovimentacaoEstoque(@NotNull(message = "Id da movimentação é obrigatória") Integer id,
                               @NotNull(message = "Quantidade não pode estar vazia") int quantidade,
                               @Size(max = 20, message = "O tipo movimentação deve ter no máximo 20 caracteres") @NotNull(message = "Tipo movimentacao é obrigatoria") TipoMovimentacao tipoMovimentacao,
                               @NotNull(message = "Data momvimentacao não pode estar vazia") LocalDateTime dataMovimentacao,
                               @Size(max = 255, message = "O campo observação deve ter no maximo 255 caracteres") String observacao,
                               Item item, Requisicao requisicao, Doacao doacao, Boolean ativo) {
        super();
        this.id = id;
        this.quantidade = quantidade;
        this.tipoMovimentacao = tipoMovimentacao;
        this.dataMovimentacao = dataMovimentacao;
        this.observacao = observacao;
        this.item = item;
        this.requisicao = requisicao;
        this.doacao = doacao;
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Requisicao getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(Requisicao requisicao) {
        this.requisicao = requisicao;
    }

    public Doacao getDoacao() {
        return doacao;
    }

    public void setDoacao(Doacao doacao) {
        this.doacao = doacao;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Boolean isAtivo() {return ativo;}

    public void setAtivo(Boolean ativo) {this.ativo = ativo;}
}

