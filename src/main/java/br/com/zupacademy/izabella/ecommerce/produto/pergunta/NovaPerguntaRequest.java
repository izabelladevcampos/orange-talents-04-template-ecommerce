package br.com.zupacademy.izabella.ecommerce.produto.pergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import br.com.zupacademy.izabella.ecommerce.produto.Produto;
import br.com.zupacademy.izabella.ecommerce.usuario.Usuario;

public class NovaPerguntaRequest {

	@NotBlank
	private String titulo;

	/*
	 * Para corrigir o problema da serialização por ter apenas um atributo
	 */
	@JsonCreator(mode = Mode.PROPERTIES)
	public NovaPerguntaRequest(@NotBlank String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public Pergunta toModel(@NotNull @Valid Usuario consumidor, @NotNull @Valid Produto produto) {
		return new Pergunta(this.titulo, consumidor, produto);

	}

}
