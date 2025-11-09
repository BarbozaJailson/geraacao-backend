package br.com.belval.api.geraacao.dto;

import br.com.belval.api.geraacao.model.*;
import jakarta.persistence.Column;
import java.time.LocalDate;

public class DoacaoResponseDTO {
    private Integer id;
    private String status;
    private int quantidade;
    private LocalDate data;
    private boolean ativo;

    // Dados do usuário
    private Integer usuarioId;
    private String usuarioNome;
    private String usuarioCpf;
    private String usuarioLogradouro;
    private String usuarioBairro;
    private String usuarioCidade;
    private String usuarioUf;
    private String usuarioNumero;
    private Boolean usuarioAtivo;

    // Dados da instituição
    private Integer instituicaoId;
    private String instituicaoNome;
    private String instituicaoCnpj;
    private String instituicaoTipoLogradouro;
    private String instituicaoTelefone;
    private String instituicaoLogradouro;
    private String instituicaoBairro;
    private String instituicaoCidade;
    private String instituicaoUf;
    private String instituicaoNumero;
    private Boolean instituicaoAtivo;

    // Dados do item
    private Integer itemId;
    private String itemNome;
    private String itemTamanho;
    private String itemGenero;
    private String itemSecao;
    private String itemTipo;
    private String itemImagem;
    private String itemDescricao;
    private Boolean itemAtivo;

    // Dados da requisição
    private Integer requisicaoId;
    private int requisicaoQuantidade;
    private Boolean requisicaoAtivo;
    private String requisicaoStatus;
    private LocalDate requisicaoData;

    public DoacaoResponseDTO(Doacao doacao) {
        this.id = doacao.getId();
        this.status = doacao.getStatus();
        this.quantidade = doacao.getQuantidade();
        this.data = doacao.getData();
        this.ativo = doacao.isAtivo();

        Usuario u = doacao.getUsuario();
        if (u != null) {
            this.usuarioId = u.getId();
            this.usuarioNome = u.getNome();
            this.usuarioCpf = u.getCpf();
            this.usuarioLogradouro = u.getLogradouro();
            this.usuarioBairro = u.getBairro();
            this.usuarioCidade = u.getCidade();
            this.usuarioUf = u.getUf();
            this.usuarioNumero = u.getNumero();
            this.usuarioAtivo = u.isAtivo();
        }

        Requisicao r = doacao.getRequisicao();
        if (r != null) {
            this.requisicaoId = r.getId();
            this.requisicaoQuantidade = r.getQuantidade();
            this.requisicaoStatus = r.getStatus();
            this.requisicaoData = r.getData();
            this.requisicaoAtivo = r.isAtivo();

            Item item = r.getItem();
            if (item != null) {
                this.itemId = item.getId();
                this.itemNome = item.getNome();
                this.itemTamanho = item.getTamanho();
                this.itemGenero = item.getGenero();
                this.itemSecao = item.getSecao();
                this.itemTipo = item.getTipo();
                this.itemImagem = item.getImagem();
                this.itemDescricao = item.getDescricao();
                this.itemAtivo = item.isAtivo();
            }

            Instituicao inst = r.getInstituicao();
            if (inst != null) {
                this.instituicaoId = inst.getId();
                this.instituicaoNome = inst.getNome();
                this.instituicaoCnpj = inst.getCnpj();
                this.instituicaoLogradouro = inst.getLogradouro();
                this.instituicaoTipoLogradouro = inst.getTipoLogradouro();
                this.instituicaoTelefone = inst.getTelefone();
                this.instituicaoBairro = inst.getBairro();
                this.instituicaoCidade = inst.getCidade();
                this.instituicaoUf = inst.getUf();
                this.instituicaoNumero = inst.getNumero();
                this.instituicaoAtivo = inst.isAtivo();
            }
        }
    }

    // Getters e setters

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public int getQuantidade() {return quantidade;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}

    public LocalDate getData() {return data;}
    public void setData(LocalDate data) {this.data = data;}

    public Boolean isAtivo() {return ativo;}
    public void setAtivo(Boolean ativo) {this.ativo = ativo;}

    //Getters ans Setters Usuario
    public Integer getUsuarioId() {return usuarioId;}
    public void setUsuarioId(Integer usuarioId) {this.usuarioId = usuarioId;}

    public String getUsuarioNome() {return usuarioNome;}
    public void setUsuarioNome(String usuarioNome) {this.usuarioNome = usuarioNome;}

    public String getUsuarioCpf() {return usuarioCpf;}
    public void setUsuarioCpf(String usuarioCpf) {this.usuarioCpf = usuarioCpf;}

    public String getUsuarioLogradouro() {return usuarioLogradouro;}
    public void setUsuarioLogradouro(String usuarioLogradouro) {this.usuarioLogradouro = usuarioLogradouro;}

    public String getUsuarioBairro() {return usuarioBairro;}
    public void setUsuarioBairro(String usuarioBairro) {this.usuarioBairro = usuarioBairro;}

    public String getUsuarioCidade() {return usuarioCidade;}
    public void setUsuarioCidade(String usuarioCidade) {this.usuarioCidade = usuarioCidade;}

    public String getUsuarioUf() {return usuarioUf;}
    public void setUsuarioUf(String usuarioUf) {this.usuarioUf = usuarioUf;}

    public String getUsuarioNumero() {return usuarioNumero;}
    public void setUsuarioNumero(String usuarioNumero) {this.usuarioNumero = usuarioNumero;}

    public Boolean isUsuarioAtivo() {return usuarioAtivo;}
    public void setUsuarioAtivo(Boolean usuarioAtivo) {this.usuarioAtivo = usuarioAtivo;}

    //Getters and Setters Instituição
    public Integer getInstituicaoId() {return instituicaoId;}
    public void setInstituicaoId(Integer instituicaoId) {this.instituicaoId = instituicaoId;}

    public String getInstituicaoNome() {return instituicaoNome;}
    public void setInstituicaoNome(String instituicaoNome) {this.instituicaoNome = instituicaoNome;}

    public String getInstituicaoCnpj() {return instituicaoCnpj;}
    public void setInstituicaoCnpj(String instituicaoCnpj) {this.instituicaoCnpj = instituicaoCnpj;}

    public String getInstituicaoTipoLogradouro() {return instituicaoTipoLogradouro;}
    public void setInstituicaoTipoLogradouro(String instituicaoTipoLogradouro) {this.instituicaoTipoLogradouro = instituicaoTipoLogradouro;}

    public String getInstituicaoTelefone() {return instituicaoTelefone;}
    public void setInstituicaoTelefone(String instituicaoTelefone) {this.instituicaoTelefone = instituicaoTelefone;}

    public String getInstituicaoLogradouro() {return instituicaoLogradouro;}
    public void setInstituicaoLogradouro(String instituicaoLogradouro) {this.instituicaoLogradouro = instituicaoLogradouro;}

    public String getInstituicaoBairro() {return instituicaoBairro;}
    public void setInstituicaoBairro(String instituicaoBairro) {this.instituicaoBairro = instituicaoBairro;}

    public String getInstituicaoCidade() {return instituicaoCidade;}
    public void setInstituicaoCidade(String instituicaoCidade) {this.instituicaoCidade = instituicaoCidade;}

    public String getInstituicaoUf() {return instituicaoUf;}
    public void setInstituicaoUf(String instituicaoUf) {this.instituicaoUf = instituicaoUf;}

    public String getInstituicaoNumero() {return instituicaoNumero;}
    public void setInstituicaoNumero(String instituicaoNumero) {this.instituicaoNumero = instituicaoNumero;}

    public Boolean isInstituicaoAtivo() {return instituicaoAtivo;}
    public void setInstituicaoAtivo(Boolean instituicaoAtivo) {this.instituicaoAtivo = instituicaoAtivo;}

    //Getters and Setters Item
    public Integer getItemId() {return itemId;}
    public void setItemId(Integer itemId) {this.itemId = itemId;}

    public String getItemNome() {return itemNome;}
    public void setItemNome(String itemNome) {this.itemNome = itemNome;}

    public String getItemTamanho() {return itemTamanho;}
    public void setItemTamanho(String itemTamanho) {this.itemTamanho = itemTamanho;}

    public String getItemGenero() {return itemGenero;}
    public void setItemGenero(String itemGenero) {this.itemGenero = itemGenero;}

    public String getItemSecao() {return itemSecao;}
    public void setItemSecao(String itemSecao) {this.itemSecao = itemSecao;}

    public String getItemTipo() {return itemTipo;}
    public void setItemTipo(String itemTipo) {this.itemTipo = itemTipo;}

    public String getItemImagem() {return itemImagem;}
    public void setItemImagem(String itemImagem) {this.itemImagem = itemImagem;}

    public String getItemDescricao() {return itemDescricao;}
    public void setItemDescricao(String itemDescricao) {this.itemDescricao = itemDescricao;}

    public Boolean isItemAtivo() {return itemAtivo;}
    public void setItemAtivo(Boolean itemAtivo) {this.itemAtivo = itemAtivo;}

    //Getters and Setters Requisição
    public Integer getRequisicaoId() {return requisicaoId;}
    public void setRequisicaoId(Integer requisicaoId) {this.requisicaoId = requisicaoId;}

    public int getRequisicaoQuantidade() {return requisicaoQuantidade;}
    public void setRequisicaoQuantidade(int requisicaoQuantidade) {this.requisicaoQuantidade = requisicaoQuantidade;}

    public String getRequisicaoStatus() {return requisicaoStatus;}
    public void setRequisicaoStatus(String requisicaoStatus) {this.requisicaoStatus = requisicaoStatus;}

    public LocalDate getRequisicaoData() {return requisicaoData;}
    public void setRequisicaoData(LocalDate requisicaoData) {this.requisicaoData = requisicaoData;}

    public Boolean isRequisicaoAtivo() {return requisicaoAtivo;}
    public void setRequisicaoAtivo(Boolean requisicaoAtivo) {this.requisicaoAtivo = requisicaoAtivo;}
}

