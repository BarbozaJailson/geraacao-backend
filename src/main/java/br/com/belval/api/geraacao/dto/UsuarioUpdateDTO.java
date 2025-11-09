package br.com.belval.api.geraacao.dto;

import java.time.LocalDate;

import br.com.belval.api.geraacao.model.TipoUser;
import jakarta.persistence.Column;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Size;

public class UsuarioUpdateDTO {

    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;
    @Size(max = 14, message = "O CPF deve ter no máximo 14 caracteres")
    private String cpf;
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
    private String email;
    @Size(max = 100, message = "A senha deve ter no máximo 100 caracteres")
    private String senha;
    @Size(max = 100, message = "A senha deve ter no máximo 100 caracteres")
    private String novaSenha;
    private LocalDate dataNascimento; // poderia ser LocalDate, ajuste conforme seu modelo
    @Size(max = 10, message = "O CEP deve ter no máximo 10 caracteres")
    private String cep;
    @Size(max = 10, message = "O tipo de logradouro deve ter no máximo 10 caracteres")
    private String tipoLogradouro;
    @Size(max = 100, message = "O logradouro deve ter no máximo 100 caracteres")
    private String logradouro;
    @Size(max = 50, message = "O bairro deve ter no máximo 50 caracteres")
    private String bairro;
    @Size(max = 50, message = "A cidade deve ter no máximo 50 caracteres")
    private String cidade;
    @Size(max = 2, message = "O UF deve ter no máximo 2 caracteres")
    private String uf;
    @Size(max = 10, message = "O número deve ter no máximo 10 caracteres")
    private String numero;
    @Size(max = 20, message = "O tipo do usuário deve ter no máximo 20 caracteres")
    private String tipoUser;
    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    private String telefone;
    private MultipartFile imagem;  // Para atualizar a imagem opcionalmente
    @Column(name = "ativo", nullable = false, length = 1)
    private Boolean ativo;

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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCep() {return cep;}
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

    public String getTipoUser() {return tipoUser;}
    public void setTipoUser(String tipoUser) {
        this.tipoUser = tipoUser;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public MultipartFile getImagem() {
        return imagem;
    }
    public void setImagem(MultipartFile imagem) {
        this.imagem = imagem;
    }

    public Boolean isAtivo() {return ativo;}
    public void setAtivo(Boolean ativo) {this.ativo = ativo;}

    public String getNovaSenha() {return novaSenha;}
    public void setNovaSenha(String novaSenha) {this.novaSenha = novaSenha;}
}


