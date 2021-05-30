package br.com.zupacademy.izabella.ecommerce.produto.detalhe;

import br.com.zupacademy.izabella.ecommerce.produto.Produto;
import br.com.zupacademy.izabella.ecommerce.produto.caracteristica.CaracteristicaProdutoResponse;
import br.com.zupacademy.izabella.ecommerce.produto.imagem.ImagemProduto;
import br.com.zupacademy.izabella.ecommerce.produto.opiniao.OpiniaoResponse;
import br.com.zupacademy.izabella.ecommerce.produto.pergunta.Pergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class ProdutoDetalheResponse {
    private Long id;
    private String nome;
    private BigDecimal valor;
    private String descricao;
    private Double mediaNotas;
    private Integer quantidadeNotas;
    private Set<CaracteristicaProdutoResponse> caracteristicas;
    private Set<String> imagens;
    private List<OpiniaoResponse> opinioes;
    private Set<String> perguntas;

    public ProdutoDetalheResponse(@NotNull @Valid Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.descricao = produto.getDescricao();
        this.caracteristicas = produto.mapCaracteristicas(CaracteristicaProdutoResponse::new);
        this.imagens = produto.mapImagens(ImagemProduto::getLink);
        this.perguntas = produto.mapPerguntas(Pergunta::getTitulo);
        this.opinioes = produto.mapOpinioes(OpiniaoResponse::new);
        this.mediaNotas = opinioes.stream().mapToDouble(OpiniaoResponse::getNota).average().orElse(0.0);
        this.quantidadeNotas = opinioes.size();
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

    public String getDescricao() {
        return descricao;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Integer getQuantidadeNotas() {
        return quantidadeNotas;
    }

    public Set<CaracteristicaProdutoResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getImagens() {
        return imagens;
    }

    public List<OpiniaoResponse> getOpinioes() {
        return opinioes;
    }

    public Set<String> getPerguntas() {
        return perguntas;
    }
}