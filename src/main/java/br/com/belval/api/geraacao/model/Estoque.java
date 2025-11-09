package br.com.belval.api.geraacao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "quantidade")
    private Integer quantidade;
    // Relacionamento entre classes
    @ManyToOne
    @JoinColumn(name = "id_item", nullable = false)
    private Item item;
    // Relacionamento entre classes
    @ManyToOne
    @JoinColumn(name = "id_instituicao", nullable = false)
    private Instituicao instituicao;

    public Estoque() {

    }

    public Estoque(Integer id, Item item, Instituicao instituicao, Integer quantidade) {
        super();
        this.id = id;
        this.item = item;
        this.instituicao = instituicao;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}


