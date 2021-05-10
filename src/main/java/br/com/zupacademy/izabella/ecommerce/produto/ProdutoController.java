package br.com.zupacademy.izabella.ecommerce.produto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.izabella.ecommerce.usuario.Usuario;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@PersistenceContext
	private EntityManager manager;
	
	
	@InitBinder(value = "novoProdutoRequest")
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeCaracteristicasComNomeIgualValidator());
	}

	@PostMapping
	@Transactional
	public ResponseEntity<NovoProdutoRequest> criaProduto(@RequestBody @Valid NovoProdutoRequest request, @AuthenticationPrincipal Usuario usuario){
		Produto produto = request.toModel(manager, usuario);
		manager.persist(produto);
		return ResponseEntity.ok().build();
	}

}
