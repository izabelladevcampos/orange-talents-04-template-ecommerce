package br.com.zupacademy.izabella.ecommerce.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaLimpa {

	private final String senha;

	public SenhaLimpa(String senha) {
		this.senha = senha;
	}

	public String hash() {
		return new BCryptPasswordEncoder().encode(senha);
	}

}
