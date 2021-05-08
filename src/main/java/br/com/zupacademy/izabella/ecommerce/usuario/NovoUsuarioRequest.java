package br.com.zupacademy.izabella.ecommerce.usuario;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.izabella.ecommerce.compartilhado.validacao.UniqueValue;

public class NovoUsuarioRequest {

	@NotBlank
	@UniqueValue(domainClass = Usuario.class, fieldName = "login")
	private String login;

	@NotBlank
	@Length(min = 6)
	private String senha;

	public NovoUsuarioRequest(@NotBlank String login, @NotBlank String senha) {
		this.login = login;
		this.senha = senha;
	}

	public Usuario toModel() {
		return new Usuario(this.login, new SenhaLimpa(senha));
	}
}
