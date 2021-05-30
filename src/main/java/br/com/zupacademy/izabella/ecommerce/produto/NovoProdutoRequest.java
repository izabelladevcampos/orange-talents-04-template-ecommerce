package br.com.zupacademy.izabella.ecommerce.produto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.izabella.ecommerce.compartilhado.validacao.ExistsValue;
import br.com.zupacademy.izabella.ecommerce.produto.caracteristica.NovaCaracteristicaProdutoRequest;
import br.com.zupacademy.izabella.ecommerce.produto.categoria.Categoria;
import br.com.zupacademy.izabella.ecommerce.usuario.Usuario;

public class NovoProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    private Integer quantidade;

    @Size(min = 3)
    @Valid
    private Set<NovaCaracteristicaProdutoRequest> caracteristicas;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @ExistsValue(targetClass = Categoria.class, fieldName = "id")
    private Long idCategoria;

    public NovoProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull Integer quantidade,
                              @Size(min = 3) @Valid Set<NovaCaracteristicaProdutoRequest> caracteristicas,
                              @NotBlank @Length(max = 1000) String descricao, @NotNull Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
    }

    public Set<String> buscarCaracteristicasIguais() {
        Set<String> nomesIguais = new HashSet<>();
        Set<String> resultados = new HashSet<>();
        for (NovaCaracteristicaProdutoRequest c : caracteristicas) {
            String nome = c.getNome();
            if (!nomesIguais.add(nome)) {
                resultados.add(nome);
            }
        }
        return resultados;
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

    public Set<NovaCaracteristicaProdutoRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public Produto toModel(EntityManager manager, Usuario usuario) {
        Categoria categoria = manager.find(Categoria.class, idCategoria);
        Usuario usuarioLogado = manager.find(Usuario.class, usuario.getId());

        return new Produto(this.nome, this.valor, this.quantidade, this.descricao, categoria, caracteristicas,
                usuarioLogado);
    }

    @Override
    public String toString() {
        return "NovoProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", caracteristicas=" + caracteristicas +
                ", descricao='" + descricao + '\'' +
                ", idCategoria=" + idCategoria +
                '}';
    }
}
