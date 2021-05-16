package br.com.zupacademy.izabella.ecommerce.produto.caracteristica;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.izabella.ecommerce.produto.Produto;

public class NovaCaracteristicaProdutoRequest {

	@NotBlank
	private String nome;
	private String descricao;

	public NovaCaracteristicaProdutoRequest(@NotBlank String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public CaracteristicaProduto toModel(@NotNull @Valid Produto produto) {
		return new CaracteristicaProduto(this.nome, this.descricao, produto);
	}

}
