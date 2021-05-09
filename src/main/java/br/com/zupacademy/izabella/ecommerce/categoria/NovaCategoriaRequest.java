package br.com.zupacademy.izabella.ecommerce.categoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import br.com.zupacademy.izabella.ecommerce.compartilhado.validacao.ExistsValue;
import br.com.zupacademy.izabella.ecommerce.compartilhado.validacao.UniqueValue;

public class NovaCategoriaRequest {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "O nome informado para a categoria já existe")
	private String nome;

	@Positive
	@ExistsValue(targetClass = Categoria.class, fieldName = "id", required = false, message = "O id da categoria informada não existe!")
	private Long idCategoriaMae;

	public NovaCategoriaRequest(@NotBlank String nome) {
		this.nome = nome;
	}

	public NovaCategoriaRequest(@NotBlank String nome, @Positive Long idCategoriaMae) {
		this.nome = nome;
		this.idCategoriaMae = idCategoriaMae;
	}

	public Categoria toModel(EntityManager manager) {
		Categoria categoria = new Categoria(this.nome);

		if (idCategoriaMae != null) {
			Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
			Assert.notNull(categoriaMae, "Categoria não deveria ser nula.");
			categoria.setIdCategoriaMae(categoriaMae);
		}
		return categoria;

	}
}