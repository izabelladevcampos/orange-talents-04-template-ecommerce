package br.com.zupacademy.izabella.ecommerce.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioResponse {

    @NotNull
    private Long id;
    @NotBlank
    @Email
    private String login;

    public UsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}
