package br.com.zupacademy.izabella.ecommerce.produto.pergunta;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import br.com.zupacademy.izabella.ecommerce.produto.Produto;
import br.com.zupacademy.izabella.ecommerce.usuario.Usuario;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	@PastOrPresent
	private final LocalDateTime momentoCriacao = LocalDateTime.now();

	@ManyToOne
	@NotNull
	Usuario consumidor;

	@ManyToOne
	@NotNull
	Produto produto;

	@Deprecated
	public Pergunta() {

	}

	public Pergunta(String titulo, Usuario consumidor, Produto produto) {
		this.titulo = titulo;
		this.consumidor = consumidor;
		this.produto = produto;
	}

	public Object getConsumidor() {
		return consumidor;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDateTime getMomentoCriacao() {
		return momentoCriacao;
	}

	public Produto getProduto() {
		return produto;
	}

}
