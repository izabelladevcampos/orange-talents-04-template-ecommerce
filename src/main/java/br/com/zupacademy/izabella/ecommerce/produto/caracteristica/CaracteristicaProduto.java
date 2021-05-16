package br.com.zupacademy.izabella.ecommerce.produto.caracteristica;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.izabella.ecommerce.produto.Produto;

@Entity
public class CaracteristicaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	private String descricao;
	@ManyToOne
	private @NotNull @Valid Produto produto;

	@Deprecated
	public CaracteristicaProduto() {

	}

	public CaracteristicaProduto(@NotBlank String nome, String descricao, @NotNull @Valid Produto produto) {
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}
	
	
	public Long getId() {
	        return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Produto getProduto() {
		return produto;
	}

}
