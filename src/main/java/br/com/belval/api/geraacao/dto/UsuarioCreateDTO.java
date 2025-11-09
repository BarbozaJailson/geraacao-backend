package br.com.belval.api.geraacao.dto;

import java.time.LocalDate;
import br.com.belval.api.geraacao.model.TipoUser;
import jakarta.persistence.Column;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioCreateDTO {

    @NotBlank
    private String nome;
    @NotBlank
    private String cpf;
    @NotBlank
    @Email
    private String email;
    @Size(min = 6)
    private String senha;
    private MultipartFile imagem;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    @Size(min = 8, max = 8)
    private String cep;
    @Size(max = 20)
    private String tipoLogradouro;
    @Size(max = 50)
    private String logradouro;
    @Size(max = 50)
    private String bairro;
    @Size(max = 50)
    private String cidade;
    @Size(min = 2, max = 2)
    private String uf;
    @Size(max = 6)
    private String numero;
    @NotBlank
    @Size(max = 30)
    private String tipoUser;
    @Size(min = 11, max = 11)
    private String telefone;
    @Column(name = "ativo", nullable = false, length = 1)
    private Boolean ativo;
    private Integer instituicaoId;

    // Getters e Setters

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {this.cpf = cpf;}

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

    public MultipartFile getImagem() {
        return imagem;
    }
    public void setImagem(MultipartFile imagem) {
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

    public String getTipoUser() {
        return tipoUser;
    }
    public void setTipoUser(String tipoUser) {
        this.tipoUser = tipoUser;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean isAtivo() {return ativo;}
    public void setAtivo(Boolean ativo) {this.ativo = ativo;}

    public Integer getInstituicaoId() {
        return instituicaoId;
    }
    public void setInstituicaoId(Integer instituicaoId) {
        this.instituicaoId = instituicaoId;
    }

}

