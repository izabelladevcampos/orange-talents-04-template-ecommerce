package br.com.zupacademy.izabella.ecommerce.produto.opiniao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

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
public class OpiniaoController {

	@PersistenceContext
	private EntityManager manager;

	@PostMapping("/{id}/opiniao")
	@Transactional
	public ResponseEntity<?> criaOpiniao(@RequestBody @Valid NovaOpiniaoRequest request, @PathVariable Long id,
			@AuthenticationPrincipal Usuario consumidor) {
		Produto produto = manager.find(Produto.class, id);
		Opiniao opiniao = request.toModel(produto, consumidor);
		manager.persist(opiniao);
		return ResponseEntity.ok().build();

	}
}
