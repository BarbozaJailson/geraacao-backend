package br.com.belval.api.geraacao.dto;

import jakarta.persistence.Column;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class InstituicaoCreateDTO {

    @NotBlank(message = "O nome da instituição é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;
    @NotBlank(message = "O CNPJ é obrigatório")
    @Size(max = 20, message = "O CNPJ deve ter no máximo 20 caracteres")
    private String cnpj;
    @Size(max = 15, message = "O telefone deve ter no máximo 15 caracteres")
    private String telefone;
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
    private String email;
    @Size(max = 10, message = "O CEP deve ter no máximo 10 caracteres")
    private String cep;
    @Size(max = 20, message = "O tipo de logradouro deve ter no máximo 20 caracteres")
    private String tipoLogradouro;
    @Size(max = 100, message = "O logradouro deve ter no máximo 100 caracteres")
    private String logradouro;
    @Size(max = 10, message = "O número deve ter no máximo 10 caracteres")
    private String numero;
    @Size(max = 50, message = "O bairro deve ter no máximo 50 caracteres")
    private String bairro;
    @Size(max = 50, message = "A cidade deve ter no máximo 50 caracteres")
    private String cidade;
    @Size(max = 2, message = "A UF deve ter no máximo 2 caracteres")
    private String uf;
    private MultipartFile imagem;
    @Column(name = "ativo", nullable = false, length = 1)
    private Boolean ativo;

    // Getters e Setters
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getCnpj() {return cnpj;}
    public void setCnpj(String cnpj) {this.cnpj = cnpj;}

    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getCep() {return cep;}
    public void setCep(String cep) {this.cep = cep;}

    public String getTipoLogradouro() {return tipoLogradouro;}
    public void setTipoLogradouro(String tipoLogradouro) {this.tipoLogradouro = tipoLogradouro;}

    public String getLogradouro() {return logradouro;}
    public void setLogradouro(String logradouro) {this.logradouro = logradouro;}

    public String getNumero() {return numero;}
    public void setNumero(String numero) {this.numero = numero;}

    public String getBairro() {return bairro;}
    public void setBairro(String bairro) {this.bairro = bairro;}

    public String getCidade() {return cidade;}
    public void setCidade(String cidade) {this.cidade = cidade;}

    public String getUf() {return uf;}
    public void setUf(String uf) {this.uf = uf;}

    public MultipartFile getImagem() {return imagem;}
    public void setImagem(MultipartFile imagem) {this.imagem = imagem;}

    public Boolean isAtivo() {return ativo;}
    public void setAtivo(Boolean ativo) {this.ativo = ativo;}
}

