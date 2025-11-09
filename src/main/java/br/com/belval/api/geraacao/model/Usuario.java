package br.com.belval.api.geraacao.model;

import br.com.caelum.stella.bean.validation.CPF;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "nome", nullable = false, length = 100)
    @Size(max = 100, message = "O NOME deve ter no máximo 100 caracteres")
    private String nome;
    @CPF
    @Column(name = "cpf", nullable = false, length = 11, unique = true)
    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;
    @Email(message = "digite um E-mail válido")
    @Column(name = "email", nullable = false, length = 50, unique = true)
    @NotBlank(message = "O e-mail é obrigatório")
    private String email;
    @Column(name = "senha", nullable = false, length = 100)
    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;
    @Column(name = "imagem", nullable = true)
    private String imagem;
    @Column(name = "data_nascimento")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    @Column(name = "cep", nullable = true, length = 8)
    @Size(min = 8, max = 8, message = "O CEP deve ter no máximo 8 caracteres")
    private String cep;
    @Column(name = "tipo_logradouro", nullable = true, length = 20)
    @Size(max = 20, message = "O Tipo_logradouro deve ter no máximo 20 caracteres")
    private String tipoLogradouro;
    @Column(name = "logradouro", nullable = true, length = 50)
    @Size(max = 50, message = "O Logradouro deve ter no máximo 50 caracteres")
    private String logradouro;
    @Column(name = "bairro", nullable = true, length = 50)
    @Size(max = 50, message = "O Bairro deve ter no máximo 50 caracteres")
    private String bairro;
    @Column(name = "cidade", nullable = true, length = 50)
    @Size(max = 50, message = "A Cidade deve ter no máximo 50 caracteres")
    private String cidade;
    @Column(name = "uf", nullable = true, length = 20)
    @Size(min = 2, max = 2, message = "A UF deve ter 2 caracteres")
    private String uf;
    @Column(name = "numero", nullable = true, length = 6)
    @Size(max = 6, message = "O Numero deve ter no máximo 6 caracteres")
    private String numero;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_user", nullable = false, length = 30)
    private TipoUser tipoUser;
    @Column(name = "telefone", nullable = true, length = 11)
    @Size(min = 11, max = 11, message = "O Telefone deve ter 11 caracteres")
    private String telefone;
    @Column(name = "ativo", nullable = false, length = 1)
    private Boolean ativo;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Doacao> doacoes = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Instituicao_Usuario",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_instituicao"))
    private List<Instituicao> instituicoes = new ArrayList<>();

    public Usuario() {}

    public Usuario(Integer id, String nome, String cpf, String email, String senha, String imagem,
                   LocalDate dataNascimento, String cep, String tipoLogradouro, String logradouro,
                   String bairro, String cidade, String uf, String numero, TipoUser tipoUser,
                   String telefone, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.imagem = imagem;
        this.dataNascimento = dataNascimento;
        this.cep = cep;
        this.tipoLogradouro = tipoLogradouro;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.numero = numero;
        this.tipoUser = tipoUser;
        this.telefone = telefone;
        this.ativo = ativo;
    }

    //	Getters and Setters
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoUser getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(TipoUser tipoUser) {
        this.tipoUser = tipoUser;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Instituicao> getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(List<Instituicao> instituicoes) {
        this.instituicoes = instituicoes;
    }

    public Boolean isAtivo() {return ativo;}

    public void setAtivo(Boolean ativo) {this.ativo = ativo;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return id != null && id.equals(usuario.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

