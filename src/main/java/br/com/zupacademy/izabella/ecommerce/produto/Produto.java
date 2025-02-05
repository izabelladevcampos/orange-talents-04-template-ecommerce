
package br.com.zupacademy.izabella.ecommerce.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.zupacademy.izabella.ecommerce.produto.opiniao.Opiniao;
import br.com.zupacademy.izabella.ecommerce.produto.pergunta.Pergunta;
import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.izabella.ecommerce.produto.caracteristica.CaracteristicaProduto;
import br.com.zupacademy.izabella.ecommerce.produto.caracteristica.NovaCaracteristicaProdutoRequest;
import br.com.zupacademy.izabella.ecommerce.produto.categoria.Categoria;
import br.com.zupacademy.izabella.ecommerce.produto.imagem.ImagemProduto;
import br.com.zupacademy.izabella.ecommerce.usuario.Usuario;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @NotNull
    @Size(min = 3)
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @NotNull
    @ManyToOne
    private Usuario resgistroUsuario;

    @NotNull
    private LocalDateTime instanteCriacao = LocalDateTime.now();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @OneToMany(mappedBy = "produto")
    private Set<Pergunta> perguntas = new HashSet<>();

    @OneToMany(mappedBy = "produto")
    private List<Opiniao> opinioes = new ArrayList<>();


    @Deprecated
    public Produto() {

    }

    public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
                   @NotNull @PositiveOrZero Integer quantidade, @NotBlank @Length(max = 1000) String descricao,
                   @NotNull Categoria categoria, @NotNull @Size(min = 3) Set<NovaCaracteristicaProdutoRequest> caracteristicas,
                   Usuario usuario) {

        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.caracteristicas.addAll(caracteristicas.stream()
                .map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet()));
        this.resgistroUsuario = usuario;

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public Usuario getUsuarioCriador() {
        return resgistroUsuario;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    public void associaImagem(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream()
                .map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());
        this.imagens.addAll(imagens);
    }

    public boolean pertenceAoUsuario(Usuario usuarioLogado, Produto produto) {
        return usuarioLogado.getId().equals(produto.getUsuarioCriador().getId());
    }

	public <T> Set<T> mapCaracteristicas(Function<CaracteristicaProduto, T> funcaoMapeadora){
		return this.caracteristicas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	public <T> Set<T> mapImagens(Function<ImagemProduto, T> funcaoMapeadora){
		return this.imagens.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	public <T> Set<T> mapPerguntas(Function<Pergunta, T> funcaoMapeadora){
		return this.perguntas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	public <T> List<T> mapOpinioes(Function<Opiniao, T> funcaoMapeadora){
		return this.opinioes.stream().map(funcaoMapeadora).collect(Collectors.toList());
	}

}
