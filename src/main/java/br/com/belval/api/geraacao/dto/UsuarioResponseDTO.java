package br.com.belval.api.geraacao.dto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.belval.api.geraacao.model.Usuario;
import org.springframework.beans.factory.annotation.Value;

public class UsuarioResponseDTO {

    private Integer id;
    private String nome;
    private String cpf;
    private String email;
    private String imagem;
    private LocalDate dataNascimento;
    private String cep;
    private String tipoLogradouro;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String numero;
    private String tipoUser; // mudou de TipoUser para String
    private String telefone;
    private Boolean ativo;
    private List<InstituicaoResponseDTO> instituicoes;

    public UsuarioResponseDTO(Usuario usuario, String baseUrl) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.email = usuario.getEmail();
        String userImg = usuario.getImagem();
        if (userImg != null && !userImg.isBlank()) {
            if (userImg.startsWith("http")) {
                this.imagem = userImg;
            } else {
                if (!userImg.startsWith("/")) userImg = "/" + userImg;
                this.imagem = baseUrl + userImg;
            }
        } else {
            this.imagem = null;
        }        this.dataNascimento = usuario.getDataNascimento();
        this.cep = usuario.getCep();
        this.tipoLogradouro = usuario.getTipoLogradouro();
        this.logradouro = usuario.getLogradouro();
        this.bairro = usuario.getBairro();
        this.cidade = usuario.getCidade();
        this.uf = usuario.getUf();
        this.numero = usuario.getNumero();
        this.tipoUser = usuario.getTipoUser() != null ? usuario.getTipoUser().name() : null;
        this.telefone = usuario.getTelefone();
        this.ativo = usuario.isAtivo();
        this.instituicoes = usuario.getInstituicoes() != null
                ? usuario.getInstituicoes().stream()
                .map(i -> new InstituicaoResponseDTO(i, baseUrl))
                .collect(Collectors.toList())
                : Collections.emptyList();
    }

    // ==================== Getters ====================

    public Integer getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }
    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public String getCep() { return cep; }
    public String getTipoLogradouro() { return tipoLogradouro; }
    public String getLogradouro() { return logradouro; }
    public String getBairro() { return bairro; }
    public String getCidade() { return cidade; }
    public String getUf() { return uf; }
    public String getNumero() { return numero; }
    public String getTipoUser() { return typeUser(); }
    private String typeUser() { return tipoUser; }
    public String getTelefone() { return telefone; }
    public Boolean isAtivo() { return ativo; }
    public List<InstituicaoResponseDTO> getInstituicoes() { return instituicoes; }

    public Integer getInstituicaoId() {
        if (instituicoes != null && !instituicoes.isEmpty()) {
            return instituicoes.get(0).getId();
        }
        return null;
    }
    public String getInstituicaoImagem() {
        if (instituicoes != null && !instituicoes.isEmpty()) {
            return instituicoes.get(0).getImagem();
        }
        return null;
    }
    public List<Integer> getInstituicaoIds() {
        if (instituicoes != null) {
            return instituicoes.stream().map(InstituicaoResponseDTO::getId).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
