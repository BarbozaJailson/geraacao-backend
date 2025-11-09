package br.com.belval.api.geraacao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome", nullable = false, length = 50)
    @NotBlank(message = "O nome do item é obrigatório")
    @Size(max = 50, message = "O nome do item deve ter no máximo 50 caracteres")
    private String nome;
    @Column(name = "tamanho", nullable = false, length = 10)
    @NotBlank(message = "O TAMANHO é obrigatório")
    @Size(max = 10, message = "O TAMANHO deve ter no máximo 10 caracteres")
    private String tamanho;
    @Column(name = "secao", nullable = false, length = 20)
    @Size(max = 20, message = "A SEÇÂO deve ter no máximo 20 caracteres")
    private String secao;
    @Column(name = "tipo", nullable = false, length = 15)
    @Size(max = 15, message = "O TIPO deve ter no máximo 15 caracteres")
    private String tipo;
    @Column(name = "genero", nullable = false, length = 10)
    @NotBlank(message = "O GÊNERO é obrigatório")
    @Size(max = 10, message = "O GÊNERO deve ter no máximo 10 caracteres")
    private String genero;
    @Column(name = "material", nullable = true, length = 50)
    @Size(max = 50, message = "O MATERIAL deve ter no máximo 50 caracteres")
    private String material;
    @Column(name = "descricao", nullable = true)
    private String descricao;
    @Column(name = "imagem", nullable = true)
    private String imagem;
    @Column(name = "ativo", nullable = false, length = 1)
    private Boolean ativo;
    //Relacionamento com a tabela 'Campanhas'
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Campanha> campanhas = new ArrayList<>();
    //Relacionamento com a tabela 'Requisição'
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Requisicao> requisicoes = new ArrayList<>();

    //     Construtor padrão necessário para o JPA
    public Item() {}

    // Construtor completo
    public Item(Integer id, String nome, String tamanho, String secao, String tipo, String genero, String material,
                String descricao, String imagem, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.secao = secao;
        this.tipo = tipo;
        this.genero = genero;
        this.material = material;
        this.descricao = descricao;
        this.imagem = imagem;
        this.ativo = ativo;
    }

    // Getters e setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getSecao() {
        return secao;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {this.imagem = imagem;}

    public Boolean isAtivo() {return ativo;}

    public void setAtivo(Boolean ativo) {this.ativo = ativo;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item that = (Item) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

