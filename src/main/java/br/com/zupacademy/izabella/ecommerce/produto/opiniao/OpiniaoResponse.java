package br.com.zupacademy.izabella.ecommerce.produto.opiniao;

import br.com.zupacademy.izabella.ecommerce.usuario.UsuarioResponse;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OpiniaoResponse {

    @NotBlank
    private String titulo;
    @NotNull
    @Range(min = 1, max = 5)
    private Integer nota;
    @NotNull
    private UsuarioResponse consumidor;

    public OpiniaoResponse(Opiniao opiniao) {
        this.titulo = opiniao.getTitulo();
        this.nota = opiniao.getNota();
        this.consumidor = new UsuarioResponse(opiniao.getConsumidor());
    }

    /*
    Precisei criar os getters dessa classe porque em caso contrário, o jackson me devolvia 406
     */

    public String getTitulo() {
        return titulo;
    }

    public Integer getNota() {
        return nota;
    }

    public UsuarioResponse getUsuarioOpinador() {
        return consumidor;
    }
}
