package br.com.belval.api.geraacao.dto;

import br.com.belval.api.geraacao.model.Item;
import jakarta.persistence.Column;

public class ItemResponseDTO {

    private Integer id;
    private String nome;
    private String tamanho;
    private String secao;
    private String tipo;
    private String genero;
    private String material;
    private String descricao;
    private String imagem;
    private Boolean ativo;

    public ItemResponseDTO(Item item) {
        this.id = item.getId();
        this.nome = item.getNome();
        this.tamanho = item.getTamanho();
        this.secao = item.getSecao();
        this.tipo = item.getTipo();
        this.genero = item.getGenero();
        this.material = item.getMaterial();
        this.descricao = item.getDescricao();
        this.imagem = item.getImagem();
        this.ativo = item.isAtivo();
    }

    // Getters
    public Integer getId() {return id;}

    public String getNome() {return nome;}

    public String getTamanho() {return tamanho;}

    public String getSecao() {return secao;}

    public String getTipo() {return tipo;}

    public String getGenero() {return genero;}

    public String getMaterial() {return material;}

    public String getDescricao() {return descricao;}

    public String getImagem() {return imagem;}

    public Boolean isAtivo() {return ativo;}
}


