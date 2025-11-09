package br.com.belval.api.geraacao.dto;

import br.com.belval.api.geraacao.model.Estoque;
import br.com.belval.api.geraacao.model.Instituicao;
import br.com.belval.api.geraacao.model.Item;
import java.time.LocalDate;

public class EstoqueResponseDTO {

    // dados exclusivo do estoque
    private Integer estoqueId;
    private Integer estoqueQuantidade;

    // dados exclusivo do item
    private Integer itemId;
    private String itemNome;
    private String itemGenero;
    private String itemSecao;
    private String itemTamanho;
    private String itemTipo;
    private String itemDescricao;
    private String itemMaterial;
    private boolean itemAtivo;
    private String itemImagem;

    // dados exclusivos da instituicao
    private Integer instituicaoId;
    private String instituicaoNome;
    private String instituicaoCnpj;
    private String instituicaoTelefone;
    private String instituicaoEmail;
    private String instituicaoCep;
    private String instituicaoTipoLogradouro;
    private String instituicaoLogradouro;
    private String instituicaoNumero;
    private String instituicaoBairro;
    private String instituicaoCidade;
    private String instituicaoUf;
    private String instituicaoImagem;
    private LocalDate instituicaoData;
    private boolean instituicaoAtivo;

    public EstoqueResponseDTO(Estoque estoque) {

        if (estoque != null) {
            // dados do estoque
            this.estoqueId = estoque.getId();
            this.estoqueQuantidade = estoque.getQuantidade();

            // dados do item
            Item item = estoque.getItem();
            if (item != null) {
                this.itemId = item.getId();
                this.itemNome = item.getNome();
                this.itemGenero = item.getGenero();
                this.itemSecao = item.getSecao();
                this.itemTamanho = item.getTamanho();
                this.itemTipo = item.getTipo();
                this.itemDescricao = item.getDescricao();
                this.itemMaterial = item.getMaterial();
                this.itemAtivo = item.isAtivo();
                this.itemImagem = item.getImagem();
            }

            // dados da instituição
            Instituicao instituicao = estoque.getInstituicao();
            if (instituicao != null) {
                this.instituicaoId = instituicao.getId();
                this.instituicaoNome = instituicao.getNome();
                this.instituicaoCnpj = instituicao.getCnpj();
                this.instituicaoTelefone = instituicao.getTelefone();
                this.instituicaoEmail = instituicao.getEmail();
                this.instituicaoCep = instituicao.getCep();
                this.instituicaoTipoLogradouro = instituicao.getTipoLogradouro();
                this.instituicaoLogradouro = instituicao.getLogradouro();
                this.instituicaoNumero = instituicao.getNumero();
                this.instituicaoBairro = instituicao.getBairro();
                this.instituicaoCidade = instituicao.getCidade();
                this.instituicaoUf = instituicao.getUf();
                this.instituicaoImagem = instituicao.getImagem();
                this.instituicaoData = instituicao.getData();
                this.instituicaoAtivo = instituicao.isAtivo();
            }
        }

    }

    //Getters and Setters Estoque
    public Integer getEstoqueId() {return estoqueId;}
    public void setEstoqueId(Integer estoqueId) {this.estoqueId = estoqueId;}

    public Integer getEstoqueQuantidade() {return estoqueQuantidade;}
    public void setEstoqueQuantidade(Integer estoqueQuantidade) {this.estoqueQuantidade = estoqueQuantidade;}

    //Getters and Setters Item
    public Integer getItemId() {return itemId;}
    public void setItemId(Integer itemId) {this.itemId = itemId;}

    public String getItemNome() {return itemNome;}
    public void setItemNome(String itemNome) {this.itemNome = itemNome;}

    public String getItemGenero() {return itemGenero;}
    public void setItemGenero(String itemGenero) {this.itemGenero = itemGenero;}

    public String getItemSecao() {return itemSecao;}
    public void setItemSecao(String itemSecao) {this.itemSecao = itemSecao;}

    public String getItemTamanho() {return itemTamanho;}
    public void setItemTamanho(String itemTamanho) {this.itemTamanho = itemTamanho;}

    public String getItemTipo() {return itemTipo;}
    public void setItemTipo(String itemTipo) {this.itemTipo = itemTipo;}

    public String getItemDescricao() {return itemDescricao;}
    public void setItemDescricao(String itemDescricao) {this.itemDescricao = itemDescricao;}

    public String getItemMaterial() {return itemMaterial;}
    public void setItemMaterial(String itemMaterial) {this.itemMaterial = itemMaterial;}

    public String getItemImagem() {return itemImagem;}
    public void setItemImagem(String itemImagem) {this.itemImagem = itemImagem;}

    public boolean isItemAtivo() {return itemAtivo;}
    public void setItemAtivo(boolean itemAtivo) {this.itemAtivo = itemAtivo;}

    //Getters and Setters Instituição
    public Integer getInstituicaoId() {return instituicaoId;}
    public void setInstituicaoId(Integer instituicaoId) {this.instituicaoId = instituicaoId;}

    public String getInstituicaoNome() {return instituicaoNome;}
    public void setInstituicaoNome(String instituicaoNome) {this.instituicaoNome = instituicaoNome;}

    public String getInstituicaoCnpj() {return instituicaoCnpj;}
    public void setInstituicaoCnpj(String instituicaoCnpj) {this.instituicaoCnpj = instituicaoCnpj;}

    public String getInstituicaoTelefone() {return instituicaoTelefone;}
    public void setInstituicaoTelefone(String instituicaoTelefone) {this.instituicaoTelefone = instituicaoTelefone;}

    public String getInstituicaoEmail() {return instituicaoEmail;}
    public void setInstituicaoEmail(String instituicaoEmail) {this.instituicaoEmail = instituicaoEmail;}

    public String getInstituicaoCep() {return instituicaoCep;}
    public void setInstituicaoCep(String instituicaoCep) {this.instituicaoCep = instituicaoCep;}

    public String getInstituicaoTipoLogradouro() {return instituicaoTipoLogradouro;}
    public void setInstituicaoTipoLogradouro(String instituicaoTipoLogradouro) {this.instituicaoTipoLogradouro = instituicaoTipoLogradouro;}

    public String getInstituicaoLogradouro() {return instituicaoLogradouro;}
    public void setInstituicaoLogradouro(String instituicaoLogradouro) {this.instituicaoLogradouro = instituicaoLogradouro;}

    public String getInstituicaoNumero() {return instituicaoNumero;}
    public void setInstituicaoNumero(String instituicaoNumero) {this.instituicaoNumero = instituicaoNumero;}

    public String getInstituicaoBairro() {return instituicaoBairro;}
    public void setInstituicaoBairro(String instituicaoBairro) {this.instituicaoBairro = instituicaoBairro;}

    public String getInstituicaoCidade() {return instituicaoCidade;}
    public void setInstituicaoCidade(String instituicaoCidade) {this.instituicaoCidade = instituicaoCidade;}

    public String getInstituicaoUf() {return instituicaoUf;}
    public void setInstituicaoUf(String instituicaoUf) {this.instituicaoUf = instituicaoUf;}

    public String getInstituicaoImagem() {return instituicaoImagem;}
    public void setInstituicaoImagem(String instituicaoImagem) {this.instituicaoImagem = instituicaoImagem;}

    public LocalDate getInstituicaoData() {return instituicaoData;}
    public void setInstituicaoData(LocalDate instituicaoData) {this.instituicaoData = instituicaoData;}

    public boolean isInstituicaoAtivo() {return instituicaoAtivo;}
    public void setInstituicaoAtivo(boolean instituicaoAtivo) {this.instituicaoAtivo = instituicaoAtivo;}
}

