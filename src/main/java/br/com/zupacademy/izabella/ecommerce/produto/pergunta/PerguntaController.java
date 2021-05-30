package br.com.zupacademy.izabella.ecommerce.produto.pergunta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.zupacademy.izabella.ecommerce.compartilhado.email.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.izabella.ecommerce.produto.Produto;
import br.com.zupacademy.izabella.ecommerce.usuario.Usuario;

@RestController
@RequestMapping("/produtos")
public class PerguntaController {

    @PersistenceContext
    EntityManager manager;

    @Autowired
    private Mailer mailer;

    @Transactional
    @PostMapping("/{id}/perguntas")
    public ResponseEntity<NovaPerguntaRequest> criaPergunta(@RequestBody @Valid NovaPerguntaRequest request,
                                                            @PathVariable Long id, @AuthenticationPrincipal Usuario consumidor) {

        Produto produto = manager.find(Produto.class, id);
        Pergunta pergunta = request.toModel(consumidor, produto);
        manager.persist(pergunta);

        mailer.enviarEmail("testesizap@gmail.com", produto.getUsuarioCriador().getLogin()
                , "Você tem uma nova pergunta para o produto " + produto.getNome(),
                "Olá <br/> Você recebeu a seguinte pergunta: " + pergunta.getTitulo() + "<br/> Pergunta enviada pelo cliente: " + consumidor.getLogin() + ".");

        return ResponseEntity.ok().build();

    }

}
