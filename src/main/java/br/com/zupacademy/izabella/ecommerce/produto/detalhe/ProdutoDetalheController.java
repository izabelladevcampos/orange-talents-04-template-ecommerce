package br.com.zupacademy.izabella.ecommerce.produto.detalhe;

import br.com.zupacademy.izabella.ecommerce.compartilhado.erro.ValidacaoErro;
import br.com.zupacademy.izabella.ecommerce.produto.Produto;
import br.com.zupacademy.izabella.ecommerce.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/produtos/{id}")
public class ProdutoDetalheController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<?> detalhaProduto(
            @PathVariable Long id) {

        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ValidacaoErro("produto", "o produto informado não existe."));
        }
        return ResponseEntity.ok(new ProdutoDetalheResponse(produto.get()));
    }
}
