package br.com.zupacademy.izabella.ecommerce.produto.opiniao;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import br.com.zupacademy.izabella.ecommerce.produto.Produto;
import br.com.zupacademy.izabella.ecommerce.usuario.Usuario;

public class NovaOpiniaoRequest {

	@NotBlank
	private String titulo;

	@NotBlank
	@Length(max = 500)
	private String descricao;

	@NotNull
	@Range(min = 1, max = 5)
	private Integer nota;

	public NovaOpiniaoRequest(@NotBlank String titulo, @NotBlank @Length(max = 500) String descricao,
			@NotNull @Range(min = 1, max = 5) Integer nota) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.nota = nota;
	}

	public Opiniao toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario consumidor) {
		return new Opiniao(this.titulo, this.descricao, this.nota, produto, consumidor);
	}

}
