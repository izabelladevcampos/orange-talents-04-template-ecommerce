package br.com.zupacademy.izabella.ecommerce.produto.caracteristica;

public class CaracteristicaProdutoResponse {


    private String nome;
    private String descricao;

    public CaracteristicaProdutoResponse(CaracteristicaProduto caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
