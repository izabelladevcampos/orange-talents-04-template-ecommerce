package br.com.zupacademy.izabella.ecommerce.produto.opiniao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import br.com.zupacademy.izabella.ecommerce.produto.Produto;
import br.com.zupacademy.izabella.ecommerce.usuario.Usuario;

@Entity
public class Opiniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	@NotBlank
	@Length(max = 500)
	private String descricao;

	@NotNull
	@Range(min = 1, max = 5)
	private Integer nota;

	@NotNull
	@ManyToOne
	Produto produto;

	@NotNull
	@ManyToOne
	private Usuario consumidor;

	@Deprecated
	public Opiniao() {
	}

	public Opiniao(@NotBlank String titulo, @NotBlank @Length(max = 500) String descricao,
			@NotNull @Range(min = 1, max = 5) Integer nota, @NotNull Produto produto, @NotNull Usuario consumidor) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.nota = nota;
		this.produto = produto;
		this.consumidor = consumidor;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getNota() {
		return nota;
	}

	public Produto getProduto() {
		return produto;
	}

	public Usuario getConsumidor() {
		return consumidor;
	}
}
