package br.com.belval.api.geraacao.dto;

import jakarta.persistence.Column;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ItemCreateDTO {

    @NotBlank(message = "O nome do item é obrigatório")
    @Size(max = 50, message = "O nome do item deve ter no máximo 50 caracteres")
    private String nome;
    @NotBlank(message = "O tamanho é obrigatório")
    @Size(max = 10, message = "O tamanho deve ter no máximo 10 caracteres")
    private String tamanho;
    @Size(max = 20, message = "A seção deve ter no máximo 20 caracteres")
    private String secao;
    @Size(max = 15, message = "O tipo deve ter no máximo 15 caracteres")
    private String tipo;
    @NotBlank(message = "O gênero é obrigatório")
    @Size(max = 10, message = "O gênero deve ter no máximo 10 caracteres")
    private String genero;
    @Size(max = 50, message = "O material deve ter no máximo 50 caracteres")
    private String material;
    private String descricao;
    private MultipartFile imagem; // tem que ser MultipartFile se você for acessar getOriginalFilename()
    @Column(name = "ativo", nullable = false, length = 1)
    private Boolean ativo;

    // Getters e Setters
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

    public MultipartFile getImagem() {
        return imagem;
    }
    public void setImagem(MultipartFile imagem) {
        this.imagem = imagem;
    }

    public Boolean isAtivo() {return ativo;}
    public void setAtivo(Boolean ativo) {this.ativo = ativo;}
}


