package br.com.zupacademy.izabella.ecommerce.produto;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.izabella.ecommerce.produto.imagem.NovasImagensRequest;
import br.com.zupacademy.izabella.ecommerce.produto.imagem.UploaderFake;
import br.com.zupacademy.izabella.ecommerce.produto.validacao.ProibeCaracteristicasComNomeIgualValidator;
import br.com.zupacademy.izabella.ecommerce.usuario.Usuario;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UploaderFake uploaderFake;

    @InitBinder(value = "novoProdutoRequest")
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProibeCaracteristicasComNomeIgualValidator());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<NovoProdutoRequest> criaProduto(@RequestBody @Valid NovoProdutoRequest request,
                                                          @AuthenticationPrincipal Usuario usuario) {

        System.out.println(request);
        System.out.println(usuario);

        Produto produto = request.toModel(manager, usuario);
        manager.persist(produto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{id}/imagens")
    @Transactional
    public ResponseEntity<?> adicionaImagens(@Valid NovasImagensRequest request, @PathVariable Long id,
                                             @AuthenticationPrincipal Usuario usuarioLogado) {

        Set<String> links = uploaderFake.envia(request.getImagens());
        Produto produto = manager.find(Produto.class, id);

        if (produto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O produto informado não foi encontrado!");
        }

        if (!produto.pertenceAoUsuario(usuarioLogado, produto)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("somente o usuário criador pode incluir imagens no produto!");
        }

        produto.associaImagem(links);
        manager.merge(produto);
        return ResponseEntity.ok().build();
    }

}
