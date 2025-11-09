package br.com.belval.api.geraacao.dto;

public class LoginResponseWithRefreshDTO {
    private String accessToken;
    private String refreshToken;
    private UsuarioResponseDTO usuario;

    public LoginResponseWithRefreshDTO(String accessToken, String refreshToken, UsuarioResponseDTO usuario) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.usuario = usuario;
    }

    // getters e setters

    public String getAccessToken() {return accessToken;}
    public void setAccessToken(String accessToken) {this.accessToken = accessToken;}

    public String getRefreshToken() {return refreshToken;}
    public void setRefreshToken(String refreshToken) {this.refreshToken = refreshToken;}

    public UsuarioResponseDTO getUsuario() {return usuario;}
    public void setUsuario(UsuarioResponseDTO usuario) {this.usuario = usuario;}
}
