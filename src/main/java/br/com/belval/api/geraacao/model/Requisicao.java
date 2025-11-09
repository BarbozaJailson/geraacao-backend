package br.com.belval.api.geraacao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Requisicao")
public class Requisicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @PositiveOrZero(message = "A quantidade deve ser zero ou maior")
    @Column(name = "quantidade_solicitada", nullable = false)
    private int quantidade;
    @PositiveOrZero(message = "A quantidade deve ser maior que zero")
    @Column(name = "quantidade_atendida", nullable = false)
    private int quantidadeAtendida;
    @Column(name = "data")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    @Column(name = "status", nullable = false, length = 15)
    @NotBlank(message = "O Status é obrigatório")
    @Size(max = 15, message = "O Statuss deve ter no máximo 15 caracteres")
    private String status;
    @Column(name = "ativo", nullable = false, length = 1)
    private Boolean ativo;
    @ManyToOne
    @JoinColumn(name = "id_instituicao", nullable = false)
    @NotNull(message = "A instituição é obrigatória")
    private Instituicao instituicao;
    @ManyToOne
    @JoinColumn(name = "id_item", nullable = false)
    @NotNull(message = "O Item é obrigatório")
    private Item item;
    @OneToMany(mappedBy = "requisicao", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Doacao> doacoes = new ArrayList<>();

    //	Construtor Vazio
    public Requisicao() {

    }

    //	Construtor
    public Requisicao(Integer id, int quantidade, int quantidadeAtendida, LocalDate data, String status, Instituicao instituicao, Item item, List<Doacao> doacoes, Boolean ativo) {
        this.id = id;
        this.quantidade = quantidade;
        this.quantidadeAtendida = quantidadeAtendida;
        this.data = data;
        this.status = status;
        this.instituicao = instituicao;
        this.item = item;
        this.doacoes = doacoes;
        this.ativo = ativo;
    }

    @PrePersist
    public void prePersist() {
        if (this.data == null) {
            this.data = LocalDate.now();
        }
        if (this.status == null || this.status.isBlank()) {
            this.status = "Pendente";
        }
    }

    //	Getters and Setters
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

    public int getQuantidadeAtendida() {return quantidadeAtendida;}
    public void setQuantidadeAtendida(int quantidadeAtendida) {this.quantidadeAtendida = quantidadeAtendida;}

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public List<Doacao> getDoacoes() {return doacoes;}
    public void setDoacoes(List<Doacao> doacoes) {this.doacoes = doacoes;}

    public Instituicao getInstituicao() {
        return instituicao;
    }
    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }

    public int getSaldo() {
        return quantidade - quantidadeAtendida;
    }
    public void adicionarDoacao(int quantidade) {
        this.quantidadeAtendida += quantidade;
    }

    public Boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Requisicao)) return false;
        Requisicao requisicao = (Requisicao) o;
        return id != null && id.equals(requisicao.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}

