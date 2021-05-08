package br.com.zupacademy.izabella.ecommerce.usuario;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Email
	private String login;

	@NotBlank
	@Length(min = 6)
	private String senha;

	@NotNull
	private LocalDateTime instanteCriacao = LocalDateTime.now();

	public Usuario(@NotBlank @Email String login, @NotBlank @Length(min = 6) SenhaLimpa senha) {
		this.login = login;
		this.senha = senha.hash();

	}

}
