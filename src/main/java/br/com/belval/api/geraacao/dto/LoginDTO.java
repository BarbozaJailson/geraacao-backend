package br.com.belval.api.geraacao.dto;

public class LoginDTO {
    private String login; // pode ser "email" ou "cpf" — use o campo que quiser
    private String senha;
    private String cliente;

    // Getters e setters
    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}

    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}

    public String getCliente() {return cliente;}
    public void setCliente(String cliente) {this.cliente = cliente;}
}