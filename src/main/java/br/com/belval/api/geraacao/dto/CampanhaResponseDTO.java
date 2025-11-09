package br.com.belval.api.geraacao.dto;

import br.com.belval.api.geraacao.model.Campanha;
import br.com.belval.api.geraacao.model.Item;
import br.com.belval.api.geraacao.model.Instituicao;
import br.com.belval.api.geraacao.model.MovimentacaoEstoque;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CampanhaResponseDTO {

    private Integer id;
    private String descricao;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataEvento;
    private String tipoLogradouro;
    private String logradouro;
    private String numero;
    private String cep;
    private String bairro;
    private String cidade;
    private String uf;
    private Integer quantidade;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCadastro;
    private Boolean ativo;

    // Dados do Item
    private Integer itemId;
    private String itemNome;
    private String itemTamanho;
    private String itemGenero;
    private String itemSecao;
    private String itemTipo;
    private String itemMaterial;
    private String itemDescricao;
    private String itemImagem;
    private Boolean itemAtivo;

    // Dados da Instituição
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
    private Boolean instituicaoAtivo;

    public CampanhaResponseDTO(Campanha campanha) {
        this.id = campanha.getId();
        this.descricao = campanha.getDescricao();
        this.dataEvento = campanha.getDataEvento();
        this.tipoLogradouro = campanha.getTipoLogradouro();
        this.logradouro = campanha.getLogradouro();
        this.numero = campanha.getNumero();
        this.cep = campanha.getCep();
        this.bairro = campanha.getBairro();
        this.cidade = campanha.getCidade();
        this.uf = campanha.getUf();
        this.quantidade = campanha.getQuantidade();
        this.dataCadastro = campanha.getDataCadastro();
        this.ativo = campanha.isAtivo();

        // Item
        Item item = campanha.getItem();
        if (item != null) {
            this.itemId = item.getId();
            this.itemNome = item.getNome();
            this.itemTamanho = item.getTamanho();
            this.itemGenero = item.getGenero();
            this.itemSecao = item.getSecao();
            this.itemTipo = item.getTipo();
            this.itemMaterial = item.getMaterial();
            this.itemDescricao = item.getDescricao();
            this.itemImagem = item.getImagem(); // pode ser URL ou nome do arquivo
            this.itemAtivo = item.isAtivo();
        }

        // Instituição
        Instituicao inst = campanha.getInstituicao();
        if (inst != null) {
            this.instituicaoId = inst.getId();
            this.instituicaoNome = inst.getNome();
            this.instituicaoCnpj = inst.getCnpj();
            this.instituicaoTelefone = inst.getTelefone();
            this.instituicaoEmail = inst.getEmail();
            this.instituicaoCep = inst.getCep();
            this.instituicaoTipoLogradouro = inst.getTipoLogradouro();
            this.instituicaoLogradouro = inst.getLogradouro();
            this.instituicaoNumero = inst.getNumero();
            this.instituicaoBairro = inst.getBairro();
            this.instituicaoCidade = inst.getCidade();
            this.instituicaoUf = inst.getUf();
            this.instituicaoImagem = inst.getImagem(); // pode ser URL ou nome do arquivo
            this.instituicaoAtivo = inst.isAtivo();
        }
    }

    // Getters e Setters Campanha
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getDataEvento() { return dataEvento; }
    public void setDataEvento(LocalDate dataEvento) { this.dataEvento = dataEvento; }

    public String getTipoLogradouro() { return tipoLogradouro; }
    public void setTipoLogradouro(String tipoLogradouro) { this.tipoLogradouro = tipoLogradouro; }

    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getUf() { return uf; }
    public void setUf(String uf) { this.uf = uf; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public LocalDate getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }

    public Boolean isAtivo() {return ativo;}
    public void setAtivo(Boolean ativo) {this.ativo = ativo;}

    // Getters and Setters Item
    public Integer getItemId() { return itemId; }
    public void setItemId(Integer itemId) { this.itemId = itemId; }

    public String getItemNome() { return itemNome; }
    public void setItemNome(String itemNome) { this.itemNome = itemNome; }

    public String getItemTamanho() { return itemTamanho; }
    public void setItemTamanho(String itemTamanho) { this.itemTamanho = itemTamanho; }

    public String getItemGenero() { return itemGenero; }
    public void setItemGenero(String itemGenero) { this.itemGenero = itemGenero; }

    public String getItemSecao() { return itemSecao; }
    public void setItemSecao(String itemSecao) { this.itemSecao = itemSecao; }

    public String getItemTipo() { return itemTipo; }
    public void setItemTipo(String itemTipo) { this.itemTipo = itemTipo; }

    public String getItemMaterial() { return itemMaterial; }
    public void setItemMaterial(String itemMaterial) { this.itemMaterial = itemMaterial; }

    public String getItemDescricao() { return itemDescricao; }
    public void setItemDescricao(String itemDescricao) { this.itemDescricao = itemDescricao; }

    public String getItemImagem() { return itemImagem; }
    public void setItemImagem(String itemImagem) { this.itemImagem = itemImagem; }

    public Boolean isItemAtivo() {return itemAtivo;}
    public void setItemAtivo(Boolean itemAtivo) {this.itemAtivo = itemAtivo;}

    //Getters and Setters Instituição
    public Integer getInstituicaoId() { return instituicaoId; }
    public void setInstituicaoId(Integer instituicaoId) { this.instituicaoId = instituicaoId; }

    public String getInstituicaoNome() { return instituicaoNome; }
    public void setInstituicaoNome(String instituicaoNome) { this.instituicaoNome = instituicaoNome; }

    public String getInstituicaoCnpj() { return instituicaoCnpj; }
    public void setInstituicaoCnpj(String instituicaoCnpj) { this.instituicaoCnpj = instituicaoCnpj; }

    public String getInstituicaoTelefone() { return instituicaoTelefone; }
    public void setInstituicaoTelefone(String instituicaoTelefone) { this.instituicaoTelefone = instituicaoTelefone; }

    public String getInstituicaoEmail() { return instituicaoEmail; }
    public void setInstituicaoEmail(String instituicaoEmail) { this.instituicaoEmail = instituicaoEmail; }

    public String getInstituicaoCep() { return instituicaoCep; }
    public void setInstituicaoCep(String instituicaoCep) { this.instituicaoCep = instituicaoCep; }

    public String getInstituicaoTipoLogradouro() { return instituicaoTipoLogradouro; }
    public void setInstituicaoTipoLogradouro(String instituicaoTipoLogradouro) { this.instituicaoTipoLogradouro = instituicaoTipoLogradouro; }

    public String getInstituicaoLogradouro() { return instituicaoLogradouro; }
    public void setInstituicaoLogradouro(String instituicaoLogradouro) { this.instituicaoLogradouro = instituicaoLogradouro; }

    public String getInstituicaoNumero() { return instituicaoNumero; }
    public void setInstituicaoNumero(String instituicaoNumero) { this.instituicaoNumero = instituicaoNumero; }

    public String getInstituicaoBairro() { return instituicaoBairro; }
    public void setInstituicaoBairro(String instituicaoBairro) { this.instituicaoBairro = instituicaoBairro; }

    public String getInstituicaoCidade() { return instituicaoCidade; }
    public void setInstituicaoCidade(String instituicaoCidade) { this.instituicaoCidade = instituicaoCidade; }

    public String getInstituicaoUf() { return instituicaoUf; }
    public void setInstituicaoUf(String instituicaoUf) { this.instituicaoUf = instituicaoUf; }

    public String getInstituicaoImagem() { return instituicaoImagem; }
    public void setInstituicaoImagem(String instituicaoImagem) { this.instituicaoImagem = instituicaoImagem; }

    public Boolean isInstituicaoAtivo() {return instituicaoAtivo;}
    public void setInstituicaoAtivo(Boolean instituicaoAtivo) {this.instituicaoAtivo = instituicaoAtivo;}

}
