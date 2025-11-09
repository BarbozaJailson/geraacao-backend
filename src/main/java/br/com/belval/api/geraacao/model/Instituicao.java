package br.com.belval.api.geraacao.model;

import br.com.caelum.stella.bean.validation.CNPJ;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Instituicao")
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "nome", nullable = false, length = 100)
    @Size(max = 100, message = "O NOME deve ter no máximo 100 caracteres")
    private String nome;
    @CNPJ
    @NotBlank(message = "O CNPJ é obrigatório")
    @Column(name = "cnpj", nullable = false, length = 14)
    @Size(min = 14, max = 14, message = "O CNPJ deve ter 14 caracteres")
    private String cnpj;
    @NotBlank(message = "O TELEFONE é obrigatório")
    @Column(name = "telefone", nullable = false, length = 11)
    @Size(min = 11, max = 11, message = "O TELEFONE deve ter 11 numeros")
    private String telefone;
    @Email(message = "digite um E-mail válido")
    @Column(name = "email", nullable = false, length = 50)
    @Size(max = 50, message = "O E-MAIL deve ter no maximo 50 caracteres")
    private String email;
    @NotBlank(message = "O CEP é obrigatório")
    @Column(name = "cep", nullable = false, length = 8)
    @Size(min = 8, max = 8, message = "O CEP deve ter no maximo 8 caracteres")
    private String cep;
    @NotBlank(message = "O TIPO_LOGRADOURO é obrigatório")
    @Column(name = "tipo_logradouro", nullable = false, length = 20)
    @Size(max = 20, message = "O TIPO_LOGRADOURO deve ter no maximo 20 caracteres")
    private String tipoLogradouro;
    @NotBlank(message = "O LOGRADOURO é obrigatório")
    @Column(name = "logradouro", nullable = false, length = 50)
    @Size(max = 50, message = "O LOGRADOURO deve ter no maximo 50 caracteres")
    private String logradouro;
    @NotBlank(message = "O NUMERO é obrigatório")
    @Column(name = "numero", nullable = false, length = 6)
    @Size(max = 6, message = "O NUMERO deve ter no maximo 6 caracteres")
    private String numero;
    @NotBlank(message = "O BAIRRO é obrigatório")
    @Column(name = "bairro", nullable = false, length = 50)
    @Size(max = 50, message = "O BAIRRO deve ter no maximo 50 caracteres")
    private String bairro;
    @NotBlank(message = "A CIDADE é obrigatória")
    @Column(name = "cidade", nullable = false, length = 50)
    @Size(max = 50, message = "A CIDADE deve ter no maximo 50 caracteres")
    private String cidade;
    @NotBlank(message = "A UF é obrigatória")
    @Column(name = "uf", nullable = false, length = 2)
    @Size(min = 2, max = 2, message = "A UF deve ter 2 caracteres")
    private String uf;
    @Column(name = "data", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd") // se usar com Spring
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    @Column(name = "imagem", nullable = true)
    private String imagem;
    @Column(name = "ativo", nullable = false, length = 1)
    private Boolean ativo;
    // Relacionamento com 'Requisicao'
    @OneToMany(mappedBy = "instituicao", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnore
    private List<Requisicao> requisicao = new ArrayList<>();
    // Relacionamento com 'Usuario'
    @ManyToMany(mappedBy = "instituicoes", cascade = CascadeType.PERSIST)
    private List<Usuario> usuarios = new ArrayList<>();
    // Relacionamento com 'Campanha'
    @OneToMany(mappedBy = "instituicao", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnore
    private List<Campanha> campanhas = new ArrayList<>();

    public Instituicao() {}

    public Instituicao(Integer id, String nome, String cnpj, String telefone, String email, LocalDate data,
                       String imagem, String cep, String tipoLogradouro, String logradouro, String bairro,
                       String cidade, String uf, String numero, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.email = email;
        this.data = data;
        this.imagem = imagem;
        this.cep = cep;
        this.tipoLogradouro = tipoLogradouro;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.numero = numero;
        this.ativo = ativo;
    }

    // Getters e setters
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getCnpj() {return cnpj;}
    public void setCnpj(String cnpj) {this.cnpj = cnpj;}

    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public LocalDate getData() {return data;}
    public void setData(LocalDate data) {this.data = data;}

    public String getImagem() {return imagem;}
    public void setImagem(String imagem) {this.imagem = imagem;}

    public String getCep() {return cep;}
    public void setCep(String cep) {this.cep = cep;}

    public String getTipoLogradouro() {return tipoLogradouro;}
    public void setTipoLogradouro(String tipoLogradouro) {this.tipoLogradouro = tipoLogradouro;}

    public String getLogradouro() {return logradouro;}
    public void setLogradouro(String logradouro) {this.logradouro = logradouro;}

    public String getBairro() {return bairro;}
    public void setBairro(String bairro) {this.bairro = bairro;}

    public String getCidade() {return cidade;}
    public void setCidade(String cidade) {this.cidade = cidade;}

    public String getUf() {return uf;}
    public void setUf(String uf) {this.uf = uf;}

    public String getNumero() {return numero;}
    public void setNumero(String numero) {this.numero = numero;}

    public Boolean isAtivo() {return ativo;}
    public void setAtivo(Boolean ativo) {this.ativo = ativo;}

    @PrePersist
    public void prePersist() {
        this.data = LocalDate.now(); // seta a data automaticamente antes do INSERT
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instituicao that = (Instituicao) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

