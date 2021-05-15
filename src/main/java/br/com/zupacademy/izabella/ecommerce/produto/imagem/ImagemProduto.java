package br.com.zupacademy.izabella.ecommerce.produto.imagem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import br.com.zupacademy.izabella.ecommerce.produto.Produto;

@Entity
public class ImagemProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@NotNull
	@Valid
	private Produto produto;

	@URL
	@NotBlank
	private String link;

	@Deprecated
	public ImagemProduto() {

	}

	public ImagemProduto(@NotNull @Valid Produto produto, @URL String link) {
		this.produto = produto;
		this.link = link;
	}

}
