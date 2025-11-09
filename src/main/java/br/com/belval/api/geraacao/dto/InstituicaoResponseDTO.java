package br.com.belval.api.geraacao.dto;

import java.time.LocalDate;

import br.com.belval.api.geraacao.model.Instituicao;
import jakarta.persistence.Column;

public class InstituicaoResponseDTO {

    private Integer id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
    private String cep;
    private String tipoLogradouro;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String imagem;
    private LocalDate data;
    private Boolean ativo;

    public InstituicaoResponseDTO(Instituicao instituicao) {
        this.id = instituicao.getId();
        this.nome = instituicao.getNome();
        this.cnpj = instituicao.getCnpj();
        this.telefone = instituicao.getTelefone();
        this.email = instituicao.getEmail();
        this.cep = instituicao.getCep();
        this.tipoLogradouro = instituicao.getTipoLogradouro();
        this.logradouro = instituicao.getLogradouro();
        this.numero = instituicao.getNumero();
        this.bairro = instituicao.getBairro();
        this.cidade = instituicao.getCidade();
        this.uf = instituicao.getUf();
        this.imagem = instituicao.getImagem();
        this.data = instituicao.getData();
        this.ativo = instituicao.isAtivo();
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getCep() {
        return cep;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public String getImagem() {
        return imagem;
    }

    public LocalDate getData() {
        return data;
    }

    public Boolean isAtivo() { return ativo; }
}


