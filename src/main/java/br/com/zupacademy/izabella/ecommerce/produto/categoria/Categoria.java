package br.com.zupacademy.izabella.ecommerce.produto.categoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@NotBlank @Column(unique = true)
	private String nome;

	@ManyToOne
	private Categoria categoriaMae;

	@Deprecated
	public Categoria() {

	}

	public Categoria(@NotBlank String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setIdCategoriaMae(Categoria categoriaMae) {
		this.categoriaMae = categoriaMae;
	}

}
